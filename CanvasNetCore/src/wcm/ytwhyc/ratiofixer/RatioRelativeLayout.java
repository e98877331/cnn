package wcm.ytwhyc.ratiofixer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;



public class RatioRelativeLayout extends RelativeLayout{

	public RatioRelativeLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
//		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//		Display display = wm.getDefaultDisplay();
//		Point size = new Point();
//		display.getSize(size);
//		
//		RatioFixer.initialize(size.x, size.y);
		

		
	}


	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		View rootView = this.getRootView();
		rootView.setBackgroundColor(Color.BLACK);
		
		
	    //int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	    int widthSize = MeasureSpec.getSize(widthMeasureSpec);      
	    //int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	    int heightSize = MeasureSpec.getSize(heightMeasureSpec);
	    
	    RatioFixer.initialize(widthSize, heightSize);
	    
	  
	    int x  = RatioFixer.getRealWidth();
	    int y = RatioFixer.getRealHeight();
	    setMeasuredDimension(RatioFixer.getRealWidth(), RatioFixer.getRealHeight());
	}
	
	
//	protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
//	  //  double factor = (right - left) / (double)_virtualWidth;
//
//	    int  nchildren = getChildCount();
//
//	    for(int i = 0; i < nchildren; i++) {
//	        View child = getChildAt(i);
//	        LayoutParams lp = (LayoutParams) child.getLayoutParams();
//	        // Scale child according to given space
//	        float factor = RatioFixer.getRatio();
//	        child.layout((int)(lp.leftMargin * factor),
//	                     (int)(lp.topMargin * factor),
//	                     (int)((lp.leftMargin + child.getMeasuredWidth()) * factor),
//	                     (int)((lp.topMargin + child.getMeasuredHeight()) * factor ));
//	    }
//	}
	
}
