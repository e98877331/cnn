package wcm.ytwhyc.ratiofixer;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout.LayoutParams;

public class RatioActivity extends Activity {

	RatioRelativeLayout ratioLayout;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	
	
	ratioLayout = new RatioRelativeLayout(this);
	ratioLayout.post(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
onLayoutCreated();
		}
	});
	ratioLayout.setBackgroundColor(Color.GRAY);

	LayoutParams layoutParams = new LayoutParams(
			android.view.ViewGroup.LayoutParams.MATCH_PARENT,
			android.view.ViewGroup.LayoutParams.MATCH_PARENT);
	layoutParams.gravity = Gravity.CENTER;
	setContentView(ratioLayout, layoutParams);
	
}
	
public void onLayoutCreated()
{
	
}

public RatioRelativeLayout getMainLayout()
{
	return ratioLayout;
}

}
