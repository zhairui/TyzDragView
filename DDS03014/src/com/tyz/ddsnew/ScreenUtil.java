package com.tyz.ddsnew;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;

public class ScreenUtil {
	
	public static int screenWidth;
	public static int screenHeigh;
	public static int statebarHeight;

	public static void getScreenInfo(Activity context){
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeigh = dm.heightPixels;
		
		
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
			statebarHeight = context.getResources().getDimensionPixelSize(height);
//			screenHeigh =  screenHeigh - statebarHeight ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.i("屏幕宽高", screenWidth +" "+  screenHeigh);
		Log.i("状态栏高度", statebarHeight+"");
		Log.i("可用宽高", screenWidth +" "+  (screenHeigh- statebarHeight));
	}

}
