package com.haihai.picture.viewer;
 

import com.haihai.picture.viewer.util.SystemUiHider;
import com.haihai.picture.viewer.view.StaticPictureView;
import com.mobisage.android.agg.view.AdSageLayout;
import com.mobisage.android.agg.view.AdSageListener;
import com.mobisage.android.agg.view.AdSageSize;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class Welcom extends Activity  implements AdSageListener{
	
	private final static String ADSAGE_LOG_TAG = "ADF";
	private static final String AdSageLayout = null;
	
    //此处配置 您的艾德聚合平台publishId；
	

	
	//此处为sample程序中需要添加广告的view
	private LinearLayout layoutMain = null; 
	
	//此处为sample程序中艾德聚合广告view
	private AdSageLayout adSageLayout = null; ;
	private String yourPublishId = "6ad62ddb4df241ceb1100bc594f0c28b"; 
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider mSystemUiHider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.welcom_layout);
		ImageView img = new StaticPictureView(this);
		
		
		 
		//addlistenner();
		addAd();
		
	}

	private void addAd() {
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.fullscreen_content_controls);
		final AdSageLayout adSageLayout;
		
        adSageLayout = new AdSageLayout(this, "3688daf8cf2d4cddaa8f5465ff208bc8",AdSageSize.AdSageSize_Auto);	
        
        adSageLayout.setAdListener(this);
        
         
        linearLayout.addView(adSageLayout);
       
		
	}

	/*private void addlistenner() {
		findViewById(R.id.dummy_button).setOnTouchListener(
				mDelayHideTouchListener);
		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);
		mSystemUiHider = SystemUiHider.getInstance(this, contentView,
				HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider
				.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
					// Cached values.
					int mControlsHeight;
					int mShortAnimTime;

					@Override
					@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
					public void onVisibilityChange(boolean visible) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
							// If the ViewPropertyAnimator API is available
							// (Honeycomb MR2 and later), use it to animate the
							// in-layout UI controls at the bottom of the
							// screen.
							if (mControlsHeight == 0) {
								mControlsHeight = controlsView.getHeight();
							}
							if (mShortAnimTime == 0) {
								mShortAnimTime = getResources().getInteger(
										android.R.integer.config_shortAnimTime);
							}
							controlsView
									.animate()
									.translationY(visible ? 0 : mControlsHeight)
									.setDuration(mShortAnimTime);
						} else {
							// If the ViewPropertyAnimator APIs aren't
							// available, simply show or hide the in-layout UI
							// controls.
							controlsView.setVisibility(visible ? View.VISIBLE
									: View.GONE);
						}

						if (visible && AUTO_HIDE) {
							// Schedule a hide().
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});

		// Set up the user interaction to manually show or hide the system UI.
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					mSystemUiHider.toggle();
				} else {
					mSystemUiHider.show();
				}
			}
		});
		
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		delayedHide(100);
	}

	*//**
	 * Touch listener to use for in-layout UI controls to delay hiding the
	 * system UI. This is to prevent the jarring behavior of controls going away
	 * while interacting with activity UI.
	 *//*
	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				 Intent intent = new Intent(Welcom.this, MenuActivity.class);
				 startActivity(intent);
			}
			return false;
		}
	};

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};

	*//**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any
	 * previously scheduled calls.
	 *//*
	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}*/

	@Override
	public void onClickAd() {
		// TODO Auto-generated method stub
		Log.w("ads", "Ads Click  ");
	}

	@Override
	public void onCloseBannerAd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCloseFullAd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDismissScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailedReceiveAd() {
		Log.w("ads", "Ads receiveing failed ");
		
	}

	@Override
	public void onFailedReceiveFullScreenAd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFullScreenDismissScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFullScreenPresentScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPresentScreen() {
		Log.w("ads", "Ads Present ... ");
		
	}

	@Override
	public void onReceiveAd() {
		Log.w("ads", "Ads receiveing ... ");
		
	}

	@Override
	public void onReceiveFullScreenAd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSplashLoadFailed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSplshScreenDismissScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSplshScreenNoReady() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSplshScreenPresentScreen() {
		// TODO Auto-generated method stub
		
	}
}
