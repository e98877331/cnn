package ntu.csie.wcmlab.canvasnetpaid;

import ntu.csie.wcmlab.canvasnetcore.CanvasNetActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class CanvasNetPaid extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas_net_paid);
		Intent intent = new Intent();
		intent.setClass(CanvasNetPaid.this, CanvasNetActivity.class);
		startActivity(intent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.canvas_net_paid, menu);
		return true;
	}

}
