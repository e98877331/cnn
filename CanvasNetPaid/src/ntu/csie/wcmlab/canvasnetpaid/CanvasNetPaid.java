package ntu.csie.wcmlab.canvasnetpaid;

import ntu.csie.wcmlab.canvasnetcore.CanvasNetFreeActivity;
import ntu.csie.wcmlab.canvasnetcore.MyCanvas;
import ntu.csie.wcmlab.canvasnetcore.R;
import ntu.csie.wcmlab.canvasnetcore.canvasnetactivity.AboutView;
import wcm.ytwhyc.ratiofixer.RatioActivity;
import wcm.ytwhyc.ratiofixer.RatioFixer;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CanvasNetPaid extends RatioActivity {

	ImageView titleView;
	AboutView aboutView;
	Button aboutBtn;
	Button hostBtn;
	Button clientBtn;

	@Override
	public void onLayoutCreated() {
		// TODO Auto-generated method stub
		super.onLayoutCreated();

		setContentView();
		setRequestedOrientation(1);
		buildPatchNoteDialog().show();
		
	}

	private void setContentView() {

		getMainLayout().setBackgroundResource(R.drawable.canvasnet_background);
		setTitleView();
		setAbout();
		setHostBtn();
		setClientBtn();
		

	}

	private void setTitleView() {
		titleView = new ImageView(this);
		titleView.setImageResource(R.drawable.canvasnet_title);
		getMainLayout().addView(titleView,
				RatioFixer.getLayoutParam(481, 237, 32, 52));
		
		 final Animation ani1 = AnimationUtils.loadAnimation(this, R.anim.title_canvasnet_animation);
		 titleView.startAnimation(ani1);
		 titleView.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
			        Animation ani2 = AnimationUtils.loadAnimation(CanvasNetPaid.this, R.anim.title_canvasnet_anim_onclick);
			        titleView.clearAnimation();
					ani2.setAnimationListener(new AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub
						}
						@Override
						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub
						}
						@Override
						public void onAnimationEnd(Animation animation) {
							// TODO Auto-generated method stub
							titleView.clearAnimation();
							titleView.startAnimation(ani1);

							
						}
					});
					
					titleView.startAnimation(ani2);
					return false;
				}
			});
	}

	private void setAbout() {
		aboutView = new AboutView(this);
		getMainLayout().addView(aboutView, RatioFixer.getLayoutParam(768, 1230, 0, 0));
		
	
		
		aboutBtn = new Button(this);
		aboutBtn.setBackgroundResource(R.drawable.canvasnet_about_btn);
		aboutBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				aboutView.show();
			}
		});
		
		getMainLayout().addView(aboutBtn,
				RatioFixer.getLayoutParam(234, 255, 534, 0));
		

	}

	private void setHostBtn() {
		
		final Animation onclickBtnAnimation = AnimationUtils.loadAnimation(this, R.anim.click_btn_animation);
		hostBtn = new Button(this);
		hostBtn.setBackgroundResource(R.drawable.canvasnet_host_btn_up_ani);
		
		final AnimationDrawable ani = (AnimationDrawable)hostBtn.getBackground();
		ani.start();
		
		getMainLayout().addView(hostBtn,
				RatioFixer.getLayoutParam(368, 625, 42, 357));
		
		hostBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onClickHost();
			}
		});
		hostBtn.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					ani.stop();
					hostBtn.setBackgroundResource(R.drawable.canvasnet_host_btn_down);
					hostBtn.startAnimation(onclickBtnAnimation);
				}else if(event.getAction() == MotionEvent.ACTION_UP){
					
					hostBtn.setBackgroundDrawable(ani);
					ani.start();
					//ClientStartBtn.setImageDrawable(null);
				}
				return false;
			}
		});
		

	}

	private void setClientBtn() {

		final Animation onclickBtnAnimation = AnimationUtils.loadAnimation(this, R.anim.click_btn_animation);
		clientBtn = new Button(this);
		clientBtn.setBackgroundResource(R.drawable.canvasnet_client_btn_up);
		getMainLayout().addView(clientBtn,
				RatioFixer.getLayoutParam(294, 762, 428, 438));
		

		clientBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				openDialog(new ClientPositiveListener(),R.string.canvasnet_client_dialog,false);
			}
		});
		clientBtn.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					clientBtn.setBackgroundResource(R.drawable.canvasnet_client_btn_down);
					clientBtn.startAnimation(onclickBtnAnimation);
				}else if(event.getAction() == MotionEvent.ACTION_UP){
					clientBtn.setBackgroundResource(R.drawable.canvasnet_client_btn_up);
					//ClientStartBtn.setImageDrawable(null);
				}
				return false;
			}
		});

	}
	
	

	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		this.finish();
	}



	class BasePositiveListener implements DialogInterface.OnClickListener {
    	public void onClick(DialogInterface dialog, int id) {}
	}

	class HostPositiveListener extends BasePositiveListener {
		@Override
		public void onClick(DialogInterface dialog, int id) {
			// TODO Auto-generated method stub
			super.onClick(dialog, id);
			
			 onClickHost();

		}
	}
	
	private void onClickHost()
	{
		Intent intent = new Intent();
		intent.setClass(CanvasNetPaid.this, MyCanvas.class);
		Bundle bundle = new Bundle();
		bundle.putBoolean("isServer", true);
		intent.putExtras(bundle);
		
		startActivity(intent);
	}

	private EditText input;
	 private String IpCache;
	 
	 ProgressDialog connectngDialog;
	class ClientPositiveListener extends BasePositiveListener{
		@Override
		public void onClick(DialogInterface dialog, int id) {
			// TODO Auto-generated method stub
			super.onClick(dialog, id);
			
		//	connectngDialog = ProgressDialog.show(CanvasNetActivity.this, "", "Loading. Please wait...", true);
        	
			
			IpCache = input.getText().toString();
			
			if(IpCache.length() != 6)
			{
				Toast.makeText(getApplicationContext(), "Magic Number should be six numbers!", Toast.LENGTH_SHORT).show();
				return;
			}
			
			Intent intent = new Intent();
			intent.setClass(CanvasNetPaid.this, MyCanvas.class);
			Bundle bundle = new Bundle();
			bundle.putBoolean("isServer", false);
			bundle.putString("IP", IpCache);
			intent.putExtras(bundle);
			
			startActivity(intent);
			
			
			
		}
    }
	

	private void openDialog(BasePositiveListener l,int message,boolean isHost)
	{
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
       
        builder.setMessage(message);
       // builder.setCancelable(false);
        if(!isHost)
        {
        	// Set an EditText view to get user input 
        	//if(input == null)
        	input = new EditText(this);
        	input.setInputType(0x00000014);
        	if(IpCache != null)
        		input.setText(IpCache);
        	builder.setView(input);
        	
        }
        builder.setPositiveButton("Yes", l);
        
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int id) {
        	}
        });   
         
       
        AlertDialog alert = builder.create();
     
        alert.show();
		
	}
	
	private void openQRCodeDialog()
	{
		
		LayoutInflater inflater = LayoutInflater.from(CanvasNetPaid.this);  
        final View textEntryView = inflater.inflate(R.layout.qrcode_dialog, null);  
        final ProgressDialog.Builder dialog = new ProgressDialog.Builder(CanvasNetPaid.this); 
      //  dialog.setCancelable(false);  
        dialog.setTitle("Get Canvas.NET from Android Market!!");  
        dialog.setView(textEntryView);
        dialog.show();
		
	}
	

	
	private AlertDialog buildPatchNoteDialog()
	{
		//chengyan:show patch note dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Note")
		       .setMessage(R.string.patchnote)
		       .setCancelable(false)
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               
		           }
		       });

		AlertDialog alert = builder.create();
		//alert.show();
		return alert;
		
	
	}

}
