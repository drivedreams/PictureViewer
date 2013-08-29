package com.haihai.picture.viewer.view;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap; 
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

public  class ChangeablePictureView extends SurfaceView {
	private int initBitmapWidth;
	private int initBitmapHeight;
	private int currentOrignPosX;
	private int currentOrignPosY; 
	float preMovedToPosX ;
	float preMovedToPosY ;
	private int currentWidth;
	private int currentHeight;
	private int currentRate;
	private boolean isMutipulTouch;
	/**
	 * Default paint paint.
	 */
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
	private Bitmap bitmap;
	private Bitmap reCreateBitmap;
	private GestureDetector gestureDetector;
	/**
	 * IDs of images in resources.
	 */
	List<Integer> imageIds = new ArrayList<Integer>();
	int viewWidth ;
	int viewHeight ;
	
	/**
	 * URLs of images in SD cart
	 */
	List<String> imageUrls = new ArrayList<String>();
	
	/**
	 * Index of image in {@link com.haihai.picture.viewer.view.ChangeablePictureView#imageIds} 
	 */
	
	int currentIdIndex ;
	
	/**
	 * Index of image in {@link com.haihai.picture.viewer.view.ChangeablePictureView#imageUrls} 
	 */
	
	 
	 
	/**
	 * 
	 */
	
	 
	public ChangeablePictureView(Context context, Bitmap bitmap) {
		super(context);
		this.bitmap = bitmap;
		initBitmapWidth = bitmap.getWidth();
		initBitmapHeight = bitmap.getHeight();
		gestureDetector = new GestureDetector(context, new GestureListener());
		initialize(context, bitmap);
	}
	
	private void initialize(Context context, Bitmap bitmap) {
		currentOrignPosX = 0;
		currentOrignPosY = 0;
		preMovedToPosX = -1;
		preMovedToPosY = -1;
		Activity activity = (Activity)context;
		WindowManager wm = activity.getWindowManager();
		viewWidth = wm.getDefaultDisplay().getWidth();
		viewHeight = wm.getDefaultDisplay().getHeight();
		reCreateBitmap = bitmap;
		setAdaptScreenShow();
	}

	private void setAdaptScreenShow( ){
		Log.w("test", "setAdaptScreenShow");
		double scale = 1;
		if((float)viewHeight / (float)initBitmapHeight < (float) viewWidth /(float)initBitmapWidth ){
			
			scale = (double)viewHeight / (double) initBitmapHeight;
		}else{
			
			scale = (double)viewWidth /(double) initBitmapWidth ;
			Log.w("test", scale + "___" + viewHeight + "____" + initBitmapHeight);
		}
		
		Point center = new Point(0, 0);
		setScale(center, (float)scale, (float)scale);
		
	}
	
	/**
	 * 
	 * @param centerPos
	 * @param scale
	 */
	private  void setScale(Point centerPos, float scalex, float scaley){
		
			Matrix matrix = new Matrix();
			matrix.setScale(scalex, scaley);
			
			Log.w("test", scalex + "--scale--"+ scaley);
			reCreateBitmap=  Bitmap.createBitmap(bitmap, 0, 0,
					initBitmapWidth, initBitmapHeight, matrix, false);
			 
			reDraw();
		 
	}
	
	//This method should be public.
	@Override 
	public void onDraw(Canvas canvas) {
		 
        canvas.drawBitmap(reCreateBitmap, currentOrignPosX, currentOrignPosY, mPaint);  
       
    }  
	/**
	 * 
	 * @param centerPos
	 * @param width
	 * @param height
	 * @return
	 */
	private  void changePicSize(Point centerPos, int width, int height){
		 
			float scalex = (float)width / (float) initBitmapWidth  ;
			float scaley = (float)height / (float) initBitmapHeight ;
			setScale(new Point(0, 0), scalex, scaley);
	}
	
	private void moveToPos(float moveDistanceX, float moveDistanceY){
		 
		currentOrignPosX = currentOrignPosX + (int)moveDistanceX;
		currentOrignPosY = currentOrignPosY + (int)moveDistanceY;
	
		reDraw();
	}
	
	private void moveToOrign(){
		currentOrignPosX = 0;
		currentOrignPosY = 0;
	}
	private class GestureListener extends SimpleOnGestureListener{
		/**
		 * Implement to listening to double tap. When the picture`s display size is smaller than 80% of initialize size, it will be changed to the initialize size. 
		 * Otherwise, it will be changed to adapt the screen size. 		 
		 */
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			moveToOrign();
			if(reCreateBitmap.getWidth() < initBitmapWidth   && reCreateBitmap.getHeight() <   initBitmapHeight  ){
				changePicSize(new Point(0, 0), initBitmapWidth, initBitmapHeight );
			}
			else{
				setAdaptScreenShow();
			}
			return super.onDoubleTap(e);
		}
		
		 
		@Override
		public boolean onDown(MotionEvent e) {
			
			return super.onDown(e);
		}
		 
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if(e2.getX() - e1.getX() > 12 || e2.getY() - e2.getY() > 12){
				
			}
			return super.onFling(e1, e2, velocityX, velocityY);
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			super.onLongPress(e);
		}
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			 
			  if(preMovedToPosX == -1 && preMovedToPosY == -1){
					preMovedToPosX = e1.getX();
					preMovedToPosY = e1.getY();
			  }
			float disX = e2.getX() - preMovedToPosX;
			float disY  = e2.getY() - preMovedToPosY;
			preMovedToPosX = e2.getX();
			preMovedToPosY = e2.getY();
			moveToPos(disX, disY );
			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			super.onShowPress(e);
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onSingleTapConfirmed(e);
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			
			
			return super.onSingleTapUp(e);
		}
		
	}

	@Override
    public boolean onTouchEvent(MotionEvent event) { 
		 
	 gestureDetector.onTouchEvent(event);
	 switch(event.getAction()){
	 case MotionEvent.ACTION_UP: preMovedToPosX = -1;
		preMovedToPosY = -1;break;
		
	 }
        return true;
    }
	 
    private synchronized void reDraw(){
    	  final Handler handler = new Handler();

    	  new Thread(new Runnable() {
    	   @Override
    	   public void run() {
    	     
    	    handler.post(new Runnable() {
    	     public void run() {
    	    	 postInvalidate();
    	     }
    	    });
    	   }
    	  }).start();
    }
    
	
}
