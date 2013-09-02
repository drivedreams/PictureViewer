package com.haihai.picture.viewer.view;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap; 
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public  class ChangeablePictureView extends View {
	private int initBitmapWidth;
	private int initBitmapHeight;
	
	/**
	 * Used for x moving pictures
	 */
	private int currentOrignPosX;
	/**
	 * Used for y moving pictures
	 */
	private int currentOrignPosY; 
	
	/**
	 * Used for x moving pictures
	 */
	float preMovedToPosX ;
	/**
	 * Used for y moving pictures
	 */
	
	float preMovedToPosY ; 
	/**
	 * Default paint paint.
	 */
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
	private Bitmap bitmap;
	private Bitmap reCreateBitmap;
	private GestureDetector gestureDetector;
	int viewWidth ;
	int viewHeight ;
	
	/**
	 * Picture come from ID or URl
	 */
	public enum  PictureOrignKind{
		PICTURE_ORIGN_KIND_ID, PICTURE_ORIGN_KIND_URL
	};
	
	/**
	 * Current currentPictureOrignKind 
	 */
	private PictureOrignKind currentPictureOrignKind;
	 
	/**
	 * The index currently of showed picture in IDs or URLs.
	 * It should be used together with  {@link com.haihai.picture.viewer.view.ChangeablePictureView#currentPictureOrignKind }
	 */
	int currentPictureIndexInList;
	/**
	 * IDs of images in resources.
	 */
	List<Integer> imageIds = new ArrayList<Integer>();
	 
	/**
	 * URLs of images in SD cart
	 */
	List<String> imageUrls = new ArrayList<String>();
	
	public ChangeablePictureView(Context context, Bitmap bitmap) {
		super(context);
		
		initialize(context, bitmap);
	}
	
	private void initialize(Context context, Bitmap bitmap) {
		this.bitmap = bitmap;
		initBitmapWidth = bitmap.getWidth();
		initBitmapHeight = bitmap.getHeight();
		gestureDetector = new GestureDetector(context, new GestureListener());
		
		currentPictureOrignKind = PictureOrignKind.PICTURE_ORIGN_KIND_ID;
		currentPictureIndexInList = 0;
		
		
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
	
	/**
	 * Gets next displayed picture 
	 * @return
	 */
	private void nextPicture(){
		
		if(currentPictureOrignKind == PictureOrignKind.PICTURE_ORIGN_KIND_ID){
			if(currentPictureIndexInList < imageIds.size() -1){
				currentPictureIndexInList ++;
				bitmap = BitmapFactory.decodeResource(getResources(), imageIds.get(currentPictureIndexInList));
				setAdaptScreenShow();
			}
		}else{
			//TODO Add some method to get bitmap from SD card
		}
		reDraw();
	}
	
	/**
	 * Gets last displayed picture 
	 * @return
	 */
	private void lastPicture(){
		 
		if(currentPictureOrignKind == PictureOrignKind.PICTURE_ORIGN_KIND_ID){
			if(currentPictureIndexInList > 0){
				currentPictureIndexInList --;
				bitmap = BitmapFactory.decodeResource(getResources(), imageIds.get(currentPictureIndexInList));
			}
		}else{
			//TODO Add some method to get bitmap from SD card
		}
		reDraw();
	}
	private void setAdaptScreenShow( ){
		 
		double scale = 1;
		if((float)viewHeight / (float)initBitmapHeight < (float) viewWidth /(float)initBitmapWidth ){
			
			scale = (double)viewHeight / (double) initBitmapHeight;
		}else{
			
			scale = (double)viewWidth /(double) initBitmapWidth ;
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
			if(e2.getX() - e1.getX() > 12 ){
				nextPicture();
			}else{
				if(e2.getX() - e1.getX() < - 12 ){
					lastPicture();
				}
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
