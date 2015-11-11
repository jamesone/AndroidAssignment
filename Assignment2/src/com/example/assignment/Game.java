/*
 * Last edited: 12 October 2015
 * About: This class is the main activity for the game and is responsible 
 * for initiating the game board and all the methods that calculate whether the user
 * has lost or not
 * Author: James Wainwright
 * 
 */
package com.example.assignment;

import game.GameBoard;
import game.Logic;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Game extends Main {
	public static GridView gridView;
	public static Item[] gridArray = new Item[Main.GRID_SIZE*4];  // Grid size/logic/setup all determined by this value
	ImageAdapter iAdapter;

	boolean allowClick = true; // Disable the grid if set to true
	public static int[] checkWin; // Holds data to how the user has lost (vert, horiz, red, white)
	public static boolean isLost = false;
//	public static int columncount = gridArray.length/4; //Get the column count
	public static int columncount = Main.GRID_SIZE; //Get the column count
	public static int boardSize = 4; 
	
	private int checkedPrimaryColour = 0; // Primary colour choice

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		if(Main.GRID_SIZE == 4)
			gridArray = new Item[16];
		else if(Main.GRID_SIZE == 5)
			gridArray = new Item[20];
		
		checkPrimaryColourRadio(); // Check primary colour chosen (default red)
		GameBoard.mTextField = (TextView) findViewById(R.id.timer); // Create timer variable that is used in gameboard class
		GameBoard.setGrid(gridArray); // Set the grid from the game board class.
		
		setGridView(); 	// Holds onClicks & onLongClicks to change grid tiles
		resetGrid(); // This will reset the Grid when you click on restart button
	}

	public void setGridView() {
		GridView gridview = (GridView) findViewById(R.id.gridview);
		iAdapter = new ImageAdapter(this, gridArray);
		GameBoard.setNumColumns(gridArray, gridview);
		gridview.setAdapter(iAdapter);		

		// If the timer has already started then cancel it so we can create a new one
		if (GameBoard.timer != null)
			GameBoard.timer.cancel();

		if (allowClick == true) {
			// The onNormal click
			gridview.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
					
					// If they decide to click on the grid again after winning then show an alert message
					if (Logic.checkBoardFull(gridArray) == 1)
						showAlert(1, 1); // Show dialog box that they've won
					else {
						int c = 0;
							
						// If allow click is = true then run the code, if false then done (meaning they've won/lost)
						if (allowClick) {

							// Get the x&y positions of the click
							int x = position / columncount;
							int y = position % columncount;

							if (!gridArray[position].isEnabled) {
								return;
							}

							//Check the primary colour chosen
							 if (checkedPrimaryColour == 0)
								 c = gridArray[position].red();
							 else
								 c = gridArray[position].white();

							checkWinner(); // Run the checkWinner method that will check if
							((ImageView) v).setImageResource(c);
						} 
						else 
							Toast.makeText(getApplicationContext(),
									"To play again click the RESTART button", Toast.LENGTH_SHORT)
									.show();

					}

				}
			});

			// The onLong click
			gridview.setOnItemLongClickListener(new OnItemLongClickListener() {
				public boolean onItemLongClick(AdapterView<?> parent, View v,
						int position, long id) {

					// If disable click is = true then run the code, if false then completed game (meaning they've won/lost)
					if (allowClick) {
						int c = 0;
						
						// Get the x & y positioned click
						int x = position / columncount;
						int y = position % columncount;

						if (!gridArray[position].isEnabled)
							return false; //Disable click on position
							
						//Depending on the primary colour check red || white
						if (checkedPrimaryColour == 0)
							 c = gridArray[position].white();
						 else
							 c = gridArray[position].red();
						
						checkWinner(); // Run the checkWinner method that will check if 
						((ImageView) v).setImageResource(c);

					} else
						Toast.makeText(getApplicationContext(),
								"To play again click the RESTART button", Toast.LENGTH_SHORT)
								.show();
					
					return true;
				}
			});
		}

		// Start the timer
		GameBoard.startTimer();
	}

	public void checkWinner() {
		isLost = false;
		// Get the returned array to see if user has won/lost - Global so the below functions can access directly
		checkWin = Logic.checkLoss(gridArray);
	
			
	
		
		checkThreeWhiteVerticallyHorizontally();
		checkThreeRedVerticallyHorizontally();
		checkIfBoardFull();
		
	}
	
	public void timerControl(){
		if(isLost){
			GameBoard.timer.cancel();
			GameBoard.mTextField.setText("You have lost with  " + GameBoard.remain/1000 + " seconds remaining");
		}  else if(Logic.checkBoardFull(gridArray) == 1) {
			GameBoard.timer.cancel();
			GameBoard.mTextField.setText("You have won with  " + GameBoard.remain/1000 + " seconds remaining");
		}
		
//		Log.d("Remain SECONDS GAME", "seconds -->>" + GameBoard.remain/1000);
	}
	
	//Below checks if a user has lost and how
	public void checkThreeWhiteVerticallyHorizontally(){
		//checks if 3 white in a row horizontally + vertically
		if (checkWin[0] == 1 && checkWin[1] == 1) {
			allowClick = false;
			showAlert("You've lost", "Better luck next time! ");
			Toast.makeText(getApplicationContext(),
					"THREE WHITE IN A ROW VERTICALLY WHITE", Toast.LENGTH_SHORT)
					.show();
			isLost = true;
			

		} else if (checkWin[0] == 1 && checkWin[1] == 2) {
			allowClick = false;
			showAlert("You've lost", "Better luck next time! ");
			Toast.makeText(getApplicationContext(),
					"THREE WHITE IN A ROW HORIZONTALLY WHITE",
					Toast.LENGTH_SHORT).show();
			isLost = true;
		}
		timerControl();
	}
	public void checkThreeRedVerticallyHorizontally(){
		//checks if 3 red in a row horizontally + vertically
		if (checkWin[0] == 2 && checkWin[1] == 1) {
			allowClick = false;
			showAlert("You've lost", "Better luck next time! ");
			Toast.makeText(getApplicationContext(),
					"THREE RED IN A ROW VERTICALly RED", Toast.LENGTH_SHORT)
					.show();
			isLost = true;

		} else if (checkWin[0] == 2 && checkWin[1] == 2) {
			allowClick = false;
			Toast.makeText(getApplicationContext(),
					"THREE RED IN A ROW HORIZONTALLY RED", Toast.LENGTH_SHORT)
					.show();
			showAlert(2, 1);
			isLost = true;
		}
		timerControl();
	}
	
	//Check if the user has won
	public void checkIfBoardFull(){
		//checks if the board is full.	
		if (Logic.checkBoardFull(gridArray) == 1) {
			showAlert(1, 1);
			allowClick = false;
			GameBoard.timer.cancel(); // Cancel the timer when the user has won - It will pause the timer so the user can see ther time
		}
	}

	// This will reset the grid
	public void resetGrid() {
		Button reset = (Button) findViewById(R.id.restart_game);
		reset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Call the setGrid method that will reset the grid!
				GameBoard.setGrid(gridArray);
				setGridView();
				allowClick = true; //Disable clicks
			}
		});
	}
	
	//If the primary color setting changes, update the appropriate variable
	public void checkPrimaryColourRadio() {
		// Radio Groups declaration
		RadioGroup primaryColour = (RadioGroup) findViewById(R.id.radGroupPrimaryColour);

		primaryColour.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.radPrimaryWhite)
					checkedPrimaryColour = 1;
				else if (checkedId == R.id.radPrimaryRed)
					checkedPrimaryColour = 0;
			}
		});
	}

	
	// Show alert depending on the type and if they've won or not
	public void showAlert(int won, int type) {
		// Type 1 = alert dialog : Type 2 = toast dialog

		AlertDialog alertDialog = new AlertDialog.Builder(Game.this).create();
		alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Dismiss",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		// alert dialog messages
		if (won == 1 && type == 1) {
			alertDialog.setTitle("You've won!");
			alertDialog.setMessage("Nice work! You've won the game");
			
		} else if (won == 2 && type == 1){
			alertDialog.setTitle("You've lost");
			alertDialog.setMessage("Better luck next time!");
		}
			alertDialog.show(); //Show the dialog | Maybe move back into if stmts
		

		// Toast messages
		if (won == 1 && type == 2)
			Toast.makeText(getApplicationContext(), "You've won!",
					Toast.LENGTH_SHORT).show();
		
		else if (won == 1 && type == 2)
			Toast.makeText(getApplicationContext(), "You've lost!",
					Toast.LENGTH_SHORT).show();
	}

	// Custom showAlert method (own title and message)
	public void showAlert(String title, String message) {
		// Type 1 = alert dialog : Type 2 = toast dialog

		AlertDialog alertDialog = new AlertDialog.Builder(Game.this).create();
		alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Dismiss",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.show();

	}
	
	
	
	
	
	// Menu + onCreateOptions menu below
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			Intent i;
			switch (item.getItemId()) {
			case R.id.homeMenuItem:
				i = new Intent(this, Main.class);
				startActivity(i);
				return true;
			case R.id.settingMenuItem:
				i = new Intent(this, Setting.class);
				startActivity(i);
				return true;
			case R.id.highscoreMenuItem:
				i = new Intent(this, Highscores.class);
				startActivity(i);
				return true;
			case R.id.gamesLabel:
				i = new Intent(this, Game.class);
				startActivity(i);
				return true;
			case R.id.helpLabel:
				i = new Intent(this, help.class);
				startActivity(i);
				return true;
			}
			return false;
		}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}
}
