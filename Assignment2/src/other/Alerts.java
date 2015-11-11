//package other;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.widget.Toast;
//
//import com.example.assignment.Game;
//
//public class Alerts {
//	public void showAlert(int won, int type){
//		//Type 1 = alert dialog : Type 2 = toast dialog
//		
//		AlertDialog alertDialog = new AlertDialog.Builder(Game.class).create();
//		alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Dismiss",
//			    new DialogInterface.OnClickListener() {
//			        public void onClick(DialogInterface dialog, int which) {
//			            dialog.dismiss();
//			        }
//			  });
//		
//		//alert dialog messages
//		if(won == 1 && type == 1){
//			alertDialog.setTitle("You've won!");
//			alertDialog.setMessage("Nice work! You've won the game");
//			alertDialog.show();
//		} else if(won == 2 && type == 1) {
//			alertDialog.setTitle("You've lost");
//			alertDialog.setMessage("Better luck next time!");
//			alertDialog.show();
//		}
//		
//		//Toast messages
//		if(won == 1 && type == 2){
//			Toast.makeText(getApplicationContext(), "You've won!",
//			Toast.LENGTH_SHORT).show();
//		} else if(won == 1 && type == 2) {
//			Toast.makeText(getApplicationContext(), "You've lost!",
//			Toast.LENGTH_SHORT).show();
//		}
//		
//		
//	}
//
//	private Context getApplicationContext() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
