package ntu.csie.wcm;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.util.Log;
import android.view.SurfaceHolder;

public class Canvas extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.canvaslayout);
		Log.e("hello","hihihih111111");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// �Ѽ�1:�s��id, �Ѽ�2:itemId, �Ѽ�3:item����, �Ѽ�4:item�W��
		menu.add(0, 0, 0, "�ЫǦ�}");
		menu.add(0, 1, 1, "�ڦn��");
		return super.onCreateOptionsMenu(menu);
	}
}
