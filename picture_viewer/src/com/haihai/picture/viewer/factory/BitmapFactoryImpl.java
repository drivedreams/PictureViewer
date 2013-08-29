package com.haihai.picture.viewer.factory;

import com.haihai.picture.viewer.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class BitmapFactoryImpl extends View{
	 
	public BitmapFactoryImpl(Context context) {
		super(context);
	}

	public Bitmap createBitmap(int drawableID){
		return  BitmapFactory.decodeResource(getResources(),R.drawable.sexymm04);
	}
	
} 
