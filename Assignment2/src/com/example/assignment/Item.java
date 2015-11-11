	package com.example.assignment;
	
	
	public class Item {
		
		//Default RDC variables
		private int RDC;
		private int id=0;
		int state = 0;
		ImageAdapter iAdapt;
		public boolean isEnabled;
		
		//Get tiletype variables
		
		//This item class is being used in the game package
		public Item(int rdc, String title, int id, boolean isEnabled) {
			this.id = id;
			this.isEnabled = isEnabled;
			this.setRDC(rdc);
		}

//		}
		public int getId(){
			return id;
		}
		public int getRDC() {
			return RDC;
		}
	
		void setRDC(int rDC) {
			RDC = rDC;
		}
		public int red(){
			RDC = Main.tile_red;
			return RDC;
		}
		public int white(){
			RDC = Main.tile_white;
			
			return RDC;
		}
		
		
	}