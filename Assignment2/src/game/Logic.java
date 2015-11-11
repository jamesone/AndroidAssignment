package game;

/*
 * Last edited: 10 oct 2015
 * About: This class holds all the logic for determining whether the user has lost
 * or not. It will determine if the user has lost 
 * Author: James Wainwright
 * 
 */
import java.util.Random;

import com.example.assignment.Game;
import com.example.assignment.Item;
import com.example.assignment.Main;
import com.example.assignment.R;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class Logic {	
	Item[] gridArray;
	 
//	private final static byte verticalLoop = 7; //FIX THIS!!!!
	public static byte won = 0;
	static int columnCount = Main.GRID_SIZE; // Get the number of columns in grid
	private final static int verticalLoop = ( (Main.GRID_SIZE*4) /2)-1; //Get the right vertical loop value by halving gridsize and taking away 1
	
	static int loopCounter; //This is used in the for loops, depending on gridsize it will either be 5 or 4

	
	public static int[] checkLoss(Item[] gridArray) {
		int[] returned = { 0, 0 };
		
		/**
		 * THis array returns whether WHITE or RED have lost - It also returns
		 * whether it was a horizontal or vertical loss. The second index holds
		 * whether it was a horizontal or vertical first index: white = 1 and
		 * red = 2 + second index: vertical = 1 and horizontal = 2
		 */
		
		Log.d("GRID_SIZE VAL -->", "GRID_SIZE-->"+Main.GRID_SIZE);
		if( Main.GRID_SIZE == 5)
			loopCounter = 5;
		else if( Main.GRID_SIZE == 4)
			loopCounter = 4;
		
		
		Log.d("loopCounter --> = ", "loopCount = -->"+loopCounter+"  =  column count -->"+columnCount);
		Log.d("gridarray.length --> = ", "Length -->"+gridArray.length);
		// Check for horizontal losses using method created below
		for (int i = 0; i < gridArray.length; i += loopCounter) {
			if (checkHorizontalLoss("white", i, gridArray) == 1)
				returned = new int[] { 1, 2 };

			if (checkHorizontalLoss("red", i, gridArray) == 2)
				returned = new int[] { 2, 2 };
		}

		// Check for vertical losses using method below - 
		for (int i = 0; i <= verticalLoop; i++) {

			// Check for the white loss
			if (checkVerticalLoss("white", i, gridArray) == 1)
				returned = new int[] { 1, 1 };

			// Check for the red loss
			if (checkVerticalLoss("red", i, gridArray) == 2) 
				returned = new int[] { 2, 1 };
		}
		
		return returned; // Return the red/white loss and whether it was a horizontal or vertical
	}

	// Check to see if the boards full
	public static int checkBoardFull(Item[] gridArray) {
		int grey = 0;
		
		for (int i1 = 0; i1 < gridArray.length; i1++) 
			if (gridArray[i1].getRDC() == R.drawable.grey) //Game hasn't been won yet
				grey++;
		
		if (grey > 0)
			won = 2;
		else
			won = 1;
		return won; // 1 = WON : 2 = LOSS
	}

	// Check to see if there's been a vertical loss
	public static int checkVerticalLoss(String color, int i, Item[] gridArray) {
		int loss = 0; // create variable that will be returned
		
		// Check for white loss
		if (color.equals("white") && gridArray[i].getRDC() == Main.tile_white
				&& gridArray[i + Main.GRID_SIZE].getRDC() == Main.tile_white
				&& gridArray[i + Main.GRID_SIZE*2].getRDC() == Main.tile_white)
			loss = 1;

		// Check for red loss
		if (color.equals("red") && gridArray[i].getRDC() == Main.tile_red
				&& gridArray[i + Main.GRID_SIZE].getRDC() == Main.tile_red
				&& gridArray[i + Main.GRID_SIZE*2].getRDC() == Main.tile_red)
			loss = 2;
		
		return loss; // Return either 1 = white loss, 2 = red loss
	}
	
	// Change this to the horizontal if statement
	public static int checkHorizontalLoss(String color, int i, Item[] gridArray) {
		int loss = 0; // create variable that will be returned
		
		// Check for red
		if (color.equals("white") && 
				(	gridArray[i].getRDC() == Main.tile_white 		&&
				 	gridArray[i + 1].getRDC() == Main.tile_white 	&& 
					gridArray[i + 2].getRDC() == Main.tile_white
				)
			|| (
					gridArray[i + 1].getRDC() == Main.tile_white 	&& 
					gridArray[i + 2].getRDC() == Main.tile_white 	&& 
					gridArray[i + 3].getRDC() == Main.tile_white 
				)
			|| ( 	columnCount == 5 && gridArray[i + 2].getRDC() == Main.tile_white	&&
 					gridArray[i + 3].getRDC() == Main.tile_white 	&& 		
 					gridArray[i + 4].getRDC() == Main.tile_white
				)
			)
			loss = 1;
		
		
		// Check for red
		if (color.equals("red") && 
				(	gridArray[i].getRDC() == Main.tile_red 		&&
				 	gridArray[i + 1].getRDC() == Main.tile_red 	&& 
					gridArray[i + 2].getRDC() == Main.tile_red
				)
			|| (
					gridArray[i + 1].getRDC() == Main.tile_red 	&& 
					gridArray[i + 2].getRDC() == Main.tile_red 	&& 
					gridArray[i + 3].getRDC() == Main.tile_red 
				)
			|| ( 	columnCount == 5 && gridArray[i + 2].getRDC() == Main.tile_red	&&
 					gridArray[i + 3].getRDC() == Main.tile_red 	&& 		
 					gridArray[i + 4].getRDC() == Main.tile_red
				)
				
			)
			loss = 2;
		
		return loss; // Return either 1 = white loss, 2 =  red loss
	}

}
