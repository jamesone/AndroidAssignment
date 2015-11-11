package com.example.assignment;
/*
 * Setting.java: 
 * Holds all settings code and methods
 * 
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Setting extends Main {
	public static int difficulty;
	public static final String DifficultyPref = "TilePreferences";
	public static final String DIFFICULTY = "grid";
	private RadioGroup appGridSize; 
	private RadioGroup appDifficulty;
	private RadioGroup appTileTheme;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		setElementVariables();
		
		setDifficultyRadioButtons();
		
		//Theme methods
		checkIfThemeChanged();
		setThemeRadioButtonsFromStorage();
		
		//Gridsize methods
		checkIfGridSizeRdioChanged(); // Check gridsize radio buttons and setting
		setGridRdioButtons(); // Checks to see if the GRID_SIZE value has changed and check variable

		// Difficulty methods
		checkDifficultySharedPref();
		checkDifficultyRadioButtonsForChange();
		Log.d("Difficulty: -->", "--->" + difficulty);
	}
	private void setElementVariables(){
		appGridSize = (RadioGroup) findViewById(R.id.radGroupGridSize);
		appDifficulty = (RadioGroup) findViewById(R.id.radGroupDifficulty);
		appTileTheme = (RadioGroup) findViewById(R.id.radGroupTileColour);
	}
	
	// Set the grid radio buttons depending on the saved shared preference value
	public void setGridRdioButtons() {
		RadioGroup appGridRdioGroup = (RadioGroup) findViewById(R.id.radGroupGridSize); 

		// Check the radio button based on selected theme
		if (Main.GRID_SIZE == 4) {
			appGridRdioGroup.check(R.id.radSize1);
			Log.d("radio", "Gridsize 4");
		} else if (Main.GRID_SIZE == 5) {
			appGridRdioGroup.check(R.id.radSize2);
		}

		Log.d("setGridRdioButtons() GRID_SIZE = :", "--->" + GRID_SIZE);
	}

	// Check if gridsize radio button has been changed, if so, change shared pref value
	public void checkIfGridSizeRdioChanged() {
		
		appGridSize.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.radSize1) {
					setGridSizePrefrences(4);
				} else if (checkedId == R.id.radSize2) {
					setGridSizePrefrences(5);
				}
				Log.d("checkIfGridSizeChanged", "--->" + GRID_SIZE);
			}
		});
	}

	/*
	 * DIFFICULTY METHODS BELOW
	*/

	// Set difficulty shared preference value, gets
	public void setDifficultySharedPref(int dif) {
		Editor editor = sharedpreferences.edit();
		editor.putInt(DIFFICULTY, dif);
		editor.apply();
		checkDifficultySharedPref();
	}

	// Check the difficulty shared preference value, set the difficulty variable
	public void checkDifficultySharedPref() {
		// Set up preferences
		sharedpreferences = getSharedPreferences(DifficultyPref,
				Context.MODE_PRIVATE);

		if (sharedpreferences.contains(DIFFICULTY))
			difficulty = sharedpreferences.getInt(DIFFICULTY, 0);
		else
			difficulty = 1;
		
	}
	
	//On change, set shared pref value
	public void checkDifficultyRadioButtonsForChange() {
		
		appDifficulty.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.radDifEasy)
					setDifficultySharedPref(1);
				else if (checkedId == R.id.radDifMedium)
					setDifficultySharedPref(2);
				else
					setDifficultySharedPref(3);
				
			}
		});
	}
	
	public void setDifficultyRadioButtons(){
		if(difficulty == 1)
			appDifficulty.check(R.id.radDifEasy);
		else if(difficulty == 2)
			appDifficulty.check(R.id.radDifMedium);
		else
			appDifficulty.check(R.id.radDifHard);
		
	}
	

	/*
	 * THEME METHODS BELOW
	*/

	public void checkIfThemeChanged() {
		appTileTheme.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.radTheme1)
					setThemeSharedPreferences(1);
				else if (checkedId == R.id.radTheme2)
					setThemeSharedPreferences(2);
			}
		});
	}

	public void setThemeRadioButtonsFromStorage() {
		// Check the radio button based on selected theme
		if (Main.theme == 1)
			appTileTheme.check(R.id.radTheme1);
		else if (Main.theme == 2)
			appTileTheme.check(R.id.radTheme2);
	}
	

	/*
	 * DEFAULT METHODS BELOW
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

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
}
