package wcm.ytwhyc.ratiofixer;

import android.widget.RelativeLayout.LayoutParams;

public class RatioFixer {

	static int VIRTUALWIDTH = 768;
	static int VIRTUALHEIGHT = 1230;
	// final static int VIRTUALWIDTH =1230;
	// final static int VIRTUALHEIGHT = 768;
	private static RatioFixer mInstance = new RatioFixer();

	float ratio;

	int realWidth;
	int realHeight;

	private RatioFixer() {

	}

	public static LayoutParams getLayoutParam(int width, int height, int x,
			int y) {
		LayoutParams rp = new LayoutParams(getValue(width), getValue(height));
		rp.leftMargin = getValue(x);
		rp.topMargin = getValue(y);
		return rp;
	}

	public static void initialize(int pw, int ph) {
		float aspect = (float) VIRTUALHEIGHT / VIRTUALWIDTH;
		float realRatio = (float) ph / pw;
		// mInstance.ratio = VIRTUALHEIGHT/ VIRTUALWIDTH;
		// mInstance.realWidth = pw;
		// mInstance.realHeight = (int)(VIRTUALHEIGHT *mInstance.ratio);
		// mInstance.realHeight = (int)(VIRTUALHEIGHT *mInstance.ratio);

		if (realRatio < aspect) {
			mInstance.realHeight = ph;
			mInstance.realWidth = (int) (mInstance.realHeight / aspect);

		} else {
			mInstance.realWidth = pw;
			mInstance.realHeight = (int) (mInstance.realWidth * aspect);
		}

		mInstance.ratio = (float) mInstance.realWidth / VIRTUALWIDTH;
	}

	public static float getRatio() {
		return mInstance.ratio;
	}

	public static int getValue(int p) {
		return (int) (p * mInstance.ratio);
	}

	public static void setVirtualSize(int vWidth, int vHeight) {
		VIRTUALWIDTH = vWidth;
		VIRTUALHEIGHT = vHeight;
	}

	public static int getRealWidth() {
		return mInstance.realWidth;

	}

	public static int getRealHeight() {
		return mInstance.realHeight;
	}

	public RatioFixer getInstance() {
		return mInstance;
	}
}
