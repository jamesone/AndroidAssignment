package com.example.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Highscores extends Main {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscores);
	}

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
