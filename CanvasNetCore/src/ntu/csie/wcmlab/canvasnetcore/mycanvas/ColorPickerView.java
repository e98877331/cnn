package ntu.csie.wcmlab.canvasnetcore.mycanvas;

import ntu.csie.wcmlab.canvasnetcore.R;
import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;

import com.larswerkman.colorpicker.ColorPicker;
import com.larswerkman.colorpicker.OpacityBar;
import com.larswerkman.colorpicker.SVBar;
import com.larswerkman.colorpicker.ThicknessBar;

public class ColorPickerView extends LinearLayout{

	public ColorPickerView(Context context,Paint pPaint) {
		super(context);
		// TODO Auto-generated constructor stub
		View.inflate(context, R.layout.color_picker_layout, this);	
		
		ColorPicker picker = (ColorPicker) this.findViewById(R.id.picker);
		SVBar svBar = (SVBar) this.findViewById(R.id.svbar);
		OpacityBar opacityBar = (OpacityBar) this.findViewById(R.id.opacitybar);
        ThicknessBar thicknessBar = (ThicknessBar) this.findViewById(R.id.thicknessbar);
		
		picker.addSVBar(svBar);
		picker.addOpacityBar(opacityBar);
		picker.addThicknessBar(thicknessBar); 
		
		this.setClickable(true);
		

       

	  
	}

}
