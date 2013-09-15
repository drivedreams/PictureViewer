package com.haihai.picture.viewer;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

public class CategoryActivity extends Activity {
	private Map<String, Bitmap> categoryMap = new HashMap<String, Bitmap>();  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_layout); 
		String category_names[] = this.getResources().getStringArray(R.array.category_names);
		
	}
}
