package ntu.csie.wcm;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MyCanvas extends Activity{

//	MySurfaceView mView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.canvaslayout);
		
		//mView = (MySurfaceView)findViewById(R.id.mySurfaceView1);
		
		Button CcBtn;  //change color button
		Button eraserBtn;
		CcBtn = (Button) findViewById(R.id.ChangeColorBt);
		CcBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				useColorPicker();
				

			}
		});
		eraserBtn = (Button) findViewById(R.id.EraserBt);
		eraserBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				MySurfaceView.mPaint.setColor(Color.WHITE);
			


			}
		});
        

		
	}
	
	
	private void useColorPicker()
	{
		new ColorPickDialog(this , MySurfaceView.mPaint , MySurfaceView.mPaint.getColor()).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// �Ѽ�1:�s��id, �Ѽ�2:itemId, �Ѽ�3:item����, �Ѽ�4:item�W��
		menu.add(0, 0, 0, "�ЫǦ�}");
		menu.add(0, 1, 1, "�ڦn��");
		return super.onCreateOptionsMenu(menu);
	}
	


	
 
    
    

}
