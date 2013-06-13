package wcm.ytwhyc.ratiofixer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout.LayoutParams;

public class RatioActivity extends Activity {

	RatioRelativeLayout mRatioLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		onInitialize();
		
		int x, y;
		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		// if(Build.VERSION.SDK_INT>Build.VERSION_CODES.HONEYCOMB){
		// Point size = new Point();
		// display.getSize(size);
		// x = size.x;
		// y = size.y;
		// }
		// else{
		x = display.getWidth(); // deprecated
		y = display.getHeight();
		// }

		int statusBarHeight = getStatusBarHeight();

		RatioFixer.initialize(x, y - statusBarHeight);

		mRatioLayout = new RatioRelativeLayout(this);
		mRatioLayout.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				onLayoutCreated();
			}
		});
		mRatioLayout.setBackgroundColor(Color.GRAY);

		LayoutParams layoutParams = new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		layoutParams.gravity = Gravity.CENTER;
		setContentView(mRatioLayout, layoutParams);
//		getWindow().getDecorView().findViewById(android.R.id.content)
//				.setBackgroundColor(Color.RED);
	}


	
	public void onInitialize()
	{
		
	}
	
	public void onLayoutCreated() {

	}
	
	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height",
				"dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
	public RatioRelativeLayout getMainLayout() {
		return mRatioLayout;
	}
	
	public void addView(View v,int width,int height,int x,int y)
	{
		mRatioLayout.addView(v,RatioFixer.getLayoutParam(width, height, x, y));
	}

}
