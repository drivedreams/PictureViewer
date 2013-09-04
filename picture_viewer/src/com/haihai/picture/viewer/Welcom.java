package com.haihai.picture.viewer;
 

import com.haihai.picture.viewer.advertise.AbstractMobiSageMediatorAd;
import com.haihai.picture.viewer.util.FileDownload;
import com.haihai.picture.viewer.view.StaticPictureView;
import com.mobisage.android.agg.view.AdSageLayout;
import com.mobisage.android.agg.view.AdSageSize;


import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Welcom   extends AbstractMobiSageMediatorAd{
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.welcom_layout);
		new StaticPictureView(this);
		 final ImageView pic = (ImageView)findViewById(R.id.download_pic);
		 String picListUrl = "http://hot.baidupcs.com/file/3608ded5e636213b5c5159b13e0b4f9e?xcode=1633cd63bdba8624979b2ba7237f37fb63783c764c03dca5&fid=255009563-250528-1176229348&time=1378306504&sign=FDTAXER-DCb740ccc5511e5e8fedcff06b081203-DEzHA6awRN%2BrvzG2AOaf%2FuBA6mY%3D&to=hb&fm=N,B,U&expires=8h&rt=pr&r=617507837&logid=1731049635&fn=piclist.txt";
		String picURL = "http://www.baidupcs.com/thumbnail/a90305752937c735ddd5e6343a0591de?fid=255009563-250528-3395885180&time=1378303914&rt=pr&sign=FDTAR-DCb740ccc5511e5e8fedcff06b081203-jigIQkYpa%2FUTUSwdj6w7sgi9nnw%3D&expires=8h&size=c850_u580&quality=100";
		final FileDownload  fileDownload = new FileDownload();
		final String picUrls[] = fileDownload.getTxtString(picListUrl).split("|");
		Log.w("txt", picUrls.length + "----" ) ;
		/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < picUrls.length; i++){
					Log.w("txt", picUrls[i] );
					//pic.setImageBitmap(fileDownload.getBitMap(pic.getContext(), picUrls[i]));
				}
			}
		}).start();*/
		
		
		//pic.setImageBitmap(fileDownload.getBitMap(pic.getContext(), picURL));
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
