package game;

/*
 * Last edited: 11 October 2015
 * About: This class has all the required code to setup the grid and set 4
 * random tiles over the grid (2white and 2red). It also has the timer method which
 * starts the timer
 * Author: James Wainwright
 * 
 */
import java.util.Random;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.example.assignment.Game;
import com.example.assignment.ImageAdapter;
import com.example.assignment.Item;
import com.example.assignment.Main;
import com.example.assignment.R;
import com.example.assignment.Setting;

//This class holds everything to do with the gameboard itself
public class GameBoard {

	// Set and get variables that are needed for GameBoard class
	static int[] arrNum = new int[4];
	static ImageAdapter iAdapter;

	// Timer field - Set to null so we can call .cancel() after the first one
	// has started in Game.java
	public static TextView mTextField;
	public static CountDownTimer timer = null;
	static boolean alreadyStopped;
	static long total;
	static int countdownTime;
	public static long remain;

	// Create the game board using the gridArray and drawing new Item()'s
	public static void setGrid(Item[] gridArray) {
		int[] setTiles = randomNumbers();
		int red = 0, white = 0, ranSet = 0;

		// This is my algorithm for randomly placing 4 tiles on the grid
		for (int i = 0; i < gridArray.length; i++) {

			// Loop through our random numbers array and check if [z] == [i]^
			for (int z = 0; z < setTiles.length; z++) {
				if (setTiles[z] == i) {
					if ((red != 2)) {
						gridArray[i] = new Item(Main.tile_red, "red", i, false);
						red++;
						break;
					} else if ((white != 2)) {
						gridArray[i] = new Item(Main.tile_white, "white", i,
								false);
						white++;
						break;
					}
				} else {
					gridArray[i] = new Item(R.drawable.grey, "grey", i, true);
				}
			}
		}
	}

	public static void setNumColumns(Item[] gridArray, GridView gridView) {
		if (gridArray.length == 20) {
			gridView.setNumColumns(5);
			Game.boardSize = 5;
		} else if (gridArray.length == 16) {
			gridView.setNumColumns(4);
			Game.boardSize = 5;
		}

	}

	// This will create an array of random numbers
	public static int[] randomNumbers() {
		// Set the range of the random nums to be generated
		int Low = 0;
		int High = 12;

		for (int i = 0; i < 4; i++) {
			Random r = new Random(); // Generate random num
			int R = r.nextInt(High - Low) + Low;
			for (int z = 0; z < arrNum.length; z++) {
				if (arrNum[z] == R) {
					R = r.nextInt(High - Low) + Low;
				}
			}
			arrNum[i] = R;
		}
		return arrNum; // Return the generated random numbers
	}

	// Sets timer from settings
	public static void startTimer() {
		
		if(Setting.difficulty == 1)
			countdownTime = 50000;
		else if(Setting.difficulty == 2)
			countdownTime = 30000;
		else if(Setting.difficulty == 3)
			countdownTime = 18000;
			
		timer = new CountDownTimer(countdownTime, 1000) {
			
			public void onTick(long millisUntilFinished) {
				mTextField.setText("seconds remaining: " + millisUntilFinished
						/ 1000);
				remain = millisUntilFinished;

				

			}

			@Override
			public void onFinish() {
				mTextField.setText("Game over! You've ran out of time");
			}

		}.start();
	}

}
