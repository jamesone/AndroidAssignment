package com.example.assignment;


import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.SharedPreferences.Editor;


public class Main extends ActionBarActivity {
	
	//Setup shared preferences variables
	public static final String MyPREFERENCES = "TilePreferences";
	public static final String gridPREFERENCES = "GridPreferences";
	public static final String THEME = "theme";
	public static final String GRIDSIZE = "grid";

	public static int theme; // Holds the chosen theme
	
	//RDCs
	public static int tile_white;
	public static int tile_red;
	
	//GridSize
	public static int GRID_SIZE;

	static SharedPreferences sharedpreferences;
	SharedPreferences gridPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		checkTileType_SharedPreferences();
		getGridSizePreferences(); //Checks the shared prefs on startup of app
		listMenu();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	public void checkTileType_SharedPreferences(){
		// Set up preferences collection
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		
		if(sharedpreferences.contains(THEME))
			theme = sharedpreferences.getInt(THEME, 0);
		else
			theme = 1;
		
		setThemeVariables();
		
	}
	
	//Sets the global variables used to change the theme and determine what drawable to calculate
	public void setThemeVariables(){
		if(theme == 1){
			tile_red = R.drawable.red;
			tile_white = R.drawable.white;
		} else if(theme == 2){
			tile_red = R.drawable.red_gradient;
			tile_white = R.drawable.white_gradient;
		}	
	}
	
	//This will change the shared preference theme value
	public void setThemeSharedPreferences(int ID){
		Editor editor = sharedpreferences.edit();
		editor.putInt(THEME, ID);
		editor.apply();
	}
	
	//Get and set grid size variables from shared preferences
	public void getGridSizePreferences(){
		// Set up preferences collection
		gridPreferences = getSharedPreferences(gridPREFERENCES, Context.MODE_PRIVATE);
		
		if(gridPreferences.contains(GRIDSIZE)){
			setGridSizeVariable(gridPreferences.getInt(GRIDSIZE, 0));
		}
		else{
			Log.d("Main.java checkGridSizeSharedPreferences():", "---> INSIDE ELSE");
			setGridSizeVariable(4);
		}
		
		Log.d("Main.java checkGridSizeSharedPreferences():", "--->"+GRID_SIZE + " shared_pref-->"+gridPreferences.getInt(GRIDSIZE, 0));
				
	}
	
	public void setGridSizeVariable(int size){
		GRID_SIZE = size;
	}
	
	public void setGridSizePrefrences(int size){
		setGridSizeVariable(size);
		Editor editor = gridPreferences.edit();
		editor.putInt(GRIDSIZE, size);
		editor.apply();
		
	}
	
	
//	public boolean saveArray(int[] array, String arrayName) {   
//		int[] savedHighscores = loadArray("highscores");
//	    SharedPreferences prefs = getSharedPreferences("highscores", 0);  
//	    SharedPreferences.Editor editor = prefs.edit();  
//	    editor.putInt(arrayName +"_size", array.length);  
//	    for(int i=0;i<array.length;i++)  
//	        editor.putInt(arrayName + "_" + i, array[i]);  
//	    return editor.commit();  
//	} 
//	
//	public int[] loadArray(int arrayName) {  
//	    SharedPreferences prefs = getSharedPreferences("highscores", 0);  
//	    int size = prefs.getInt(arrayName + "_size", 0);  
//	    int array[] = new int[size];  
//	    for(int i=0;i<size;i++)  
//	        array[i] = prefs.getInt(arrayName + "_" + i, 0);  
//	    return array;  
//	}  
	
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
	
	public void listMenu(){
		//Part 2
        ListView listView = (ListView) findViewById(R.id.main_menu);
        String[] countries = new String[] { "Play Game", "View Highscores", "Setting","Help" };
        
       
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, android.R.id.text1, countries);
        
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent z;
				switch (position){
					case 0:
						z = new Intent(Main.this, Game.class);
						startActivity(z);
						break;
					case 1:
						z = new Intent(Main.this, Highscores.class);
						startActivity(z);
						break;
					case 2:
						z = new Intent(Main.this, Setting.class);
						startActivity(z);
						break;
					case 3:
						z = new Intent(Main.this, help.class);
						startActivity(z);
						break;
						
					}
				
				/*Create the list of capitals for the list of countries and then use the selected
			 	* ListView position to grab the capital for that country
			 	* Use the position variable as the index for the capitals array.
				*/
				String[] capitals = new String[] { "Play Game", "View Highscores", "Setting", "Help"};
				// When clicked, show a toast with the TextView text
				
        		Toast.makeText(getApplicationContext(),capitals[position],Toast.LENGTH_SHORT).show(); 
				
			} 
        });
	}

}
