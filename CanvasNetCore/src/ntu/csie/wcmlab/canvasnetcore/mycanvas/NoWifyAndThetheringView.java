package ntu.csie.wcmlab.canvasnetcore.mycanvas;

import ntu.csie.wcmlab.canvasnetcore.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class NoWifyAndThetheringView {
	
	public static void show(Context ctx)
	{
		openDialog(ctx);
	}
	
	private static void openDialog(Context ctx)
	{
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
       
        builder.setTitle(R.string.mycanvas_nowifyandthethering_title);
        builder.setMessage(R.string.mycanvas_nowifyandthethering_body);
       // builder.setCancelable(false);

        builder.setPositiveButton("Yes", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
  
         
       
        AlertDialog alert = builder.create();
     
        alert.show();
		
	}

}
