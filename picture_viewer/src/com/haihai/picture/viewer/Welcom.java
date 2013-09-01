package com.haihai.picture.viewer;
 

import com.haihai.picture.viewer.advertise.AbstractMobiSageMediatorAd;
import com.haihai.picture.viewer.view.StaticPictureView;
import com.mobisage.android.agg.view.AdSageLayout;
import com.mobisage.android.agg.view.AdSageSize;


import android.os.Bundle;
import android.widget.LinearLayout;


public class Welcom   extends AbstractMobiSageMediatorAd{
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.welcom_layout);
		new StaticPictureView(this);
		 
		addADs();
		
	}

	public void addADs() {
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.fullscreen_content_controls);
		final AdSageLayout adSageLayout;
        adSageLayout = new AdSageLayout(this, AbstractMobiSageMediatorAd.yourPublishId, AdSageSize.AdSageSize_Auto);	
        adSageLayout.setAdListener(this);
        linearLayout.addView(adSageLayout);
        
	} 
	
}
