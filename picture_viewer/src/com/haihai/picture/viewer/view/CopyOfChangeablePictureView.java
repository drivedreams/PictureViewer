package com.haihai.picture.viewer.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.view.View;
import android.widget.ImageView;

public  class CopyOfChangeablePictureView extends ImageView   implements OnGestureListener {

	public CopyOfChangeablePictureView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	Matrix matrix = new Matrix();    
    Matrix savedMatrix = new Matrix();    
  
    public ImageView image;    
    static final int NONE = 0;    
    static final int DRAG = 1;    
    static final int ZOOM = 2;    
    int mode = NONE;    
  
    PointF start = new PointF();    
    PointF mid = new PointF();    
    float oldDist = 1f;    
  
  
      
  
    @Override
	public boolean onTouchEvent(MotionEvent event) {
    	 this.image.setScaleType(ScaleType.MATRIX);    
    	  
         ImageView view = this;    
//       dumpEvent(event);     
   
         switch (event.getAction() & MotionEvent.ACTION_MASK) {  
           
         case MotionEvent.ACTION_DOWN:    
   
             Log.w("FLAG", "ACTION_DOWN");  
             matrix.set(view.getImageMatrix());    
             savedMatrix.set(matrix);    
             start.set(event.getX(), event.getY());    
             mode = DRAG;    
             break;    
         case MotionEvent.ACTION_POINTER_DOWN:    
             Log.w("FLAG", "ACTION_POINTER_DOWN");  
             oldDist = spacing(event);    
             if (oldDist > 10f) {    
                 savedMatrix.set(matrix);    
                 midPoint(mid, event);    
                 mode = ZOOM;    
             }    
             break;    
         case MotionEvent.ACTION_UP:    
             Log.w("FLAG", "ACTION_UP");  
         case MotionEvent.ACTION_POINTER_UP:    
             Log.w("FLAG", "ACTION_POINTER_UP");  
             mode = NONE;    
             break;    
         case MotionEvent.ACTION_MOVE:    
             Log.w("FLAG", "ACTION_MOVE");  
             if (mode == DRAG) {    
                 matrix.set(savedMatrix);    
                 matrix.postTranslate(event.getX() - start.x, event.getY()    
                         - start.y);    
             } else if (mode == ZOOM) {    
                 float newDist = spacing(event);    
                 if (newDist > 10f) {    
                     matrix.set(savedMatrix);    
                     float scale = newDist / oldDist;    
                     matrix.postScale(scale, scale, mid.x, mid.y);    
                 }    
             }    
             break;    
         }    
   
         view.setImageMatrix(matrix);    
           
		return super.onTouchEvent(event);
	} 
    
    private float spacing(MotionEvent event) {    
        float x = event.getX(0) - event.getX(1);    
        float y = event.getY(0) - event.getY(1);    
        return FloatMath.sqrt(x * x + y * y);    
    }    
  
    private void midPoint(PointF point, MotionEvent event) {    
        float x = event.getX(0) + event.getX(1);    
        float y = event.getY(0) + event.getY(1);    
        point.set(x / 2, y / 2);    
    }    
}
