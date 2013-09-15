package com.haihai.picture.viewer.view;

import com.haihai.picture.viewer.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

public class WelcomeView extends ImageView {

	public WelcomeView(Context context) {
		super(context);
		this.setBackgroundResource(R.drawable.splsh);
	} 
	@SuppressLint("NewApi")
	public void  leftMove(long distance, long duration){
		
	}

}
