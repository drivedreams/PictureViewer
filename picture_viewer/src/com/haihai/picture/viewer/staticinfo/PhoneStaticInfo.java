package com.haihai.picture.viewer.staticinfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

public class PhoneStaticInfo extends Activity{
	private WindowManager wm = this.getWindowManager();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public  WindowManager getScreenSize(){
		
		return wm;
		
	}
}
