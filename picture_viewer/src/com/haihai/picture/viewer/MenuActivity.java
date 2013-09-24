package com.haihai.picture.viewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.haihai.picture.viewer.view.ChangeablePictureView;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button; 
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MenuActivity extends Activity{
	private  List<Integer> imageIDs ;  
	LinearLayout linearLayout;
	Map<String, Integer> categeryMap = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		
	  /*getResources().getIntArray(R.array.category_names);*/
		initiallize();
		
	   
		showImages();
	/*	Button showGuidButton = (Button)findViewById(R.id.show_guide);
		showGuidButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showImages();
			}
		});
		*/
		
	}
	private void addImages(List<Bitmap> bitmaps){
		LinearLayout linearLayout = null;
		for (int i = 0; i < bitmaps.size(); i++) {
			if(i%2==0){
			  linearLayout = new LinearLayout(this);
			  ImageView imageView = new ImageView(this);
			  imageView.setImageBitmap(bitmaps.get(i));
			  linearLayout.addView(new ImageView(this));
			}else{
				if(linearLayout !=null){
					ImageView imageView = new ImageView(this);
					imageView.setImageBitmap(bitmaps.get(i));
					linearLayout.addView(new ImageView(this));
				}
			}
		}
	}
	 private void showImages() {
		 Bitmap bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.sexymm04);
		 ChangeablePictureView changeablePictureView = new ChangeablePictureView(this, bitmap);
		 linearLayout.addView(changeablePictureView );
	}

	private void initiallize() {
		
		 /*imageIDs = new ArrayList<Integer>();
		 readPicturesID();
		 pictureViews = readsLastSeveralUnreadPictures(0, 3);*/
		  
	}

	private List<Integer> readPicturesID(){
		
		imageIDs.add(R.drawable.feature_guide_0);
		imageIDs.add(R.drawable.feature_guide_1);
		imageIDs.add(R.drawable.feature_guide_2);
		imageIDs.add(R.drawable.feature_guide_3);
		return imageIDs;
	 }
	 
	 private List<ChangeablePictureView> readsLastSeveralUnreadPictures(int currentIndex, int count){
		 List<ChangeablePictureView> pictureViews = new ArrayList<ChangeablePictureView>();
		/* for (Integer id : imageIDs) {
			 ChangeablePictureView pictureView = new ChangeablePictureView(this, BitmapFactory.decodeResource(getResources(), id));
			 
			 pictureViews.add(pictureView);
			 
		 }*/
		 
		 return pictureViews;
		 
	 }
}
