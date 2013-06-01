package ntu.csie.wcmlab.canvasnetcore.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class NetworkStatusChecker {

	public static boolean checkIfWifyConnected(Activity context) {
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (mWifi.isConnected()) {
			Log.e("MyCanvas", "Wify on!!!!!!!!!!!!!!!!!!");
			return true;
		} else {
			Log.e("MyCanvas", "Wify off!!!!!!!!!!!!!!!!!!");
			return false;
		}
	}

	public static Boolean checkIfThethering(Activity context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Activity.WIFI_SERVICE);
		Method[] wmMethods = wifi.getClass().getDeclaredMethods();
		for (Method method : wmMethods) {
			if (method.getName().equals("isWifiApEnabled")) {

				try {
					return (Boolean)method.invoke(wifi);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	
	}

}
