package ntu.csie.wcm;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyCanvas extends Activity{

	MyCanvas mSelf;
	MySurfaceView mView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.canvaslayout);
		
		mSelf = this;
		
	
		mView = (MySurfaceView)findViewById(R.id.mySurfaceView1);
		
		
		Button CcBtn;  //change color button
		CcBtn = (Button) findViewById(R.id.ChangeColorBt);
		CcBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				useColorPicker();
			}
		});
		
		

		
		Button eraserBtn;
		eraserBtn = (Button) findViewById(R.id.EraserBt);
		eraserBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				mView.getPaint().setColor(Color.WHITE);
			}
		});
		
		Button undoBtn = (Button) findViewById(R.id.undoBt);
		undoBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click

				mView.undo();
			}
		});
		
		Button redoBtn = (Button) findViewById(R.id.redoBt);
		redoBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				mView.redo();
			}
		});
		
		Button clearBtn = (Button) findViewById(R.id.clearBt);
		clearBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				mView.clearCanvas(mSelf);
			}
		});

		
	}

	
	
	private void useColorPicker()
	{
		new ColorPickDialog(this , mView.getPaint() , mView.getPaint().getColor()).show();
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
			Intent intent = new Intent();
			intent.setClass(MyCanvas.this, ImgLoaderActivity.class);
			Bundle bundle = new Bundle();
			intent.putExtras(bundle);
			startActivityForResult(intent, 1);
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
