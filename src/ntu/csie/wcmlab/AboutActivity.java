package ntu.csie.wcmlab;

import wcm.ytwhyc.ratiofixer.RatioActivity;
import wcm.ytwhyc.ratiofixer.RatioFixer;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.widget.TextView;

public class AboutActivity extends  RatioActivity{
	
	TextView mTitle;
	TextView mContent;
	
	@Override
	public void onLayoutCreated() {
		// TODO Auto-generated method stub
		super.onLayoutCreated();
		
		getMainLayout().setBackgroundResource(R.drawable.about_bg);
		
		mTitle = new TextView(this);
		mTitle.setText(R.string.about_title);
		mTitle.setGravity(Gravity.CENTER);
		mTitle.setTextColor(Color.BLACK);
		mTitle.setTextSize(50*RatioFixer.getRatio());
		mTitle.setTypeface(Typeface.DEFAULT_BOLD);
		mTitle.setBackgroundResource(R.drawable.about_title);
		
		getMainLayout().addView(mTitle, RatioFixer.getLayoutParam(600, 300, 84, 30));//y end = 330
		
		
		mContent = new TextView(this);
		mContent.setTextColor(Color.BLACK);
		mContent.setTextSize(20*RatioFixer.getRatio());
		mContent.setText(R.string.about_content);
		mContent.setPadding(RatioFixer.getValue(10), 0, RatioFixer.getValue(10), 0);
		mContent.setMovementMethod(new ScrollingMovementMethod());
		getMainLayout().addView(mContent, RatioFixer.getLayoutParam(600, 700, 84, 400));
		
		//getMainLayout()
		
		
	}

}
