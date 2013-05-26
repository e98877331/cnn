package ntu.csie.wcmlab.canvasnetcore.canvasnetactivity;

import ntu.csie.wcmlab.canvasnetcore.R;
import wcm.ytwhyc.ratiofixer.RatioFixer;
import wcm.ytwhyc.ratiofixer.RatioRelativeLayout;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class AboutView extends RatioRelativeLayout{

	ImageView content;
	
	public AboutView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

	this.setBackgroundColor(Color.argb(150, 150, 150, 150));	
		
		
		content = new ImageView(context);
		content.setImageResource(R.drawable.canvasnet_about);
		this.addView(content,RatioFixer.getLayoutParam(768,1230, 0, 0));
		
		this.setVisibility(View.INVISIBLE);
		
		
		this.setClickable(true);
		this.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
			    AboutView.this.setVisibility(View.INVISIBLE);
				return false;
			}
		});
	}

	
	public void show()
	{
		this.setVisibility(View.VISIBLE);
		this.bringToFront();
	}
	
}
