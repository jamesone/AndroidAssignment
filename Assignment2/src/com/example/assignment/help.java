package com.example.assignment;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;

public class help extends Main {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
	
		//FindView and then load the URL for the gif
		final WebView wview = (WebView)findViewById(R.id.webView1);
		wview.loadUrl("file:///android_asset/tutorial.gif");
			
		Button btnReplay = (Button)findViewById(R.id.replay_tutorial);
		 btnReplay.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 //Reload the webview(gif)
            	 wview.reload();
             }
		 });
		
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
