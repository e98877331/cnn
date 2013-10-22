package ntu.csie.wcmlab.canvasnetcore.mycanvas;

import wcm.ytwhyc.ratiofixer.RatioFixer;
import ntu.csie.wcmlab.canvasnetcore.Commands;
import ntu.csie.wcmlab.canvasnetcore.MyCanvas;
import ntu.csie.wcmlab.canvasnetcore.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.MonthDisplayHelper;
import android.view.View;
import android.widget.Button;

public class ColorPickerDialog extends Dialog {

	ColorPickerView mView;
	Paint mPaint;
	Context mContext;
	
	OnColorDecideCallBack mColorDecideCallback;
	
	public ColorPickerDialog(Context context,Paint pPaint) {
		super(context);
		// TODO Auto-generated constructor stub
		mView = new ColorPickerView(context, pPaint);
		
	   
		mPaint = pPaint;
		mContext = context;
		
		this.setContentView(mView);
		
		this.setTitle(R.string.mycanvas_colorpickerdialog_title);
		//this.getWindow().setBackgroundDrawableResource(R.drawable.canvasnet_background);
		Button okButton = (Button) mView.findViewById(R.id.ok);
		// if button is clicked, close the custom dialog
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				mPaint.setColor(mView.getColor());
				mPaint.setStrokeWidth(mView.getThickness());
				//((MyCanvas)(mContext)).getSocket().send(new Commands.ChangeColorCmd(mPaint.getColor(),mPaint.getStrokeWidth()/RatioFixer.getRatio()));
			    if(mColorDecideCallback != null)
			    	mColorDecideCallback.run(mPaint);
				ColorPickerDialog.this.dismiss();
			}

		});
		
		Button cancelButton = (Button) mView.findViewById(R.id.cancel);
		// if button is clicked, close the custom dialog
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ColorPickerDialog.this.dismiss();
			}

		});

		//this.show();
		
	}

	public void setOnColorDecideCallback(OnColorDecideCallBack callback)
	{
		mColorDecideCallback = callback;
	}
	
	public static abstract class OnColorDecideCallBack
	{
		abstract public void run(Paint pPaint);

	}
}
