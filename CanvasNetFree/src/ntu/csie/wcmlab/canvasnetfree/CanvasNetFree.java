package ntu.csie.wcmlab.canvasnetfree;

import ntu.csie.wcmlab.canvasnetcore.CanvasNetActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class CanvasNetFree extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas_net_free);
		Intent intent = new Intent();
		intent.setClass(CanvasNetFree.this, CanvasNetActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.canvas_net_free, menu);
		return true;
	}

}
