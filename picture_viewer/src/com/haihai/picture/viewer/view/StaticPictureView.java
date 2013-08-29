package com.haihai.picture.viewer.view;

import android.content.Context;
import android.widget.ImageView;

public class StaticPictureView extends ImageView{

	public StaticPictureView(Context context) {
		super(context);
		 
	}
	 
	@Override
	public void setImageResource(int resId) {
		 
		super.setImageResource(resId);
	}
	
}
