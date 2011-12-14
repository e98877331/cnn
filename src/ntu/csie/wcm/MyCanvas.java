package ntu.csie.wcm;


import java.util.HashMap;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
		menu.add(0, 0, 0, "next page");
		menu.add(0, 1, 1, "frame select");
		menu.add(0, 2, 2, "load image");
		menu.add(0, 3, 3, "check ip");
		menu.add(0, 4, 4, "disconnect");
		menu.add(0, 5, 5, "�ڦn��!");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// �̾�itemId�ӧP�_�ϥΪ��I����@��item
		switch (item.getItemId()) {
		case 0:
			Toast.makeText(MyCanvas.this, "�٨S��", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			Toast.makeText(MyCanvas.this, "�٨S��", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			Toast.makeText(MyCanvas.this, "���b��", Toast.LENGTH_SHORT).show();
			break;
		case 3:
			LayoutInflater inflater = LayoutInflater.from(MyCanvas.this);  
	        final View textEntryView = inflater.inflate(R.layout.dialog, null);  
	        final TextView ipTextView=(TextView)textEntryView.findViewById(R.id.ipTextView);
	        String ip = "192.168.xxx.test";
	        ipTextView.setText(ip);
	        final ProgressDialog.Builder builder = new ProgressDialog.Builder(MyCanvas.this); 
	        builder.setCancelable(false);  
	        builder.setTitle("�ЫǦ�}");  
	        builder.setView(textEntryView);
	        builder.setNegativeButton("�T�w",  
	                new DialogInterface.OnClickListener() {  
	                    public void onClick(DialogInterface dialog, int whichButton) {  
	                        setTitle("");  
	                    }  
	                });  
	        builder.show();
			break;
		case 4:
			this.finish();
			break;
		case 5:
			Toast.makeText(MyCanvas.this, "����", Toast.LENGTH_SHORT).show();
			break;
		default:
		}
		return super.onOptionsItemSelected(item);
	}
	


	
 
    
    

}
