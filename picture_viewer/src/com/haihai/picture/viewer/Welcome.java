package com.haihai.picture.viewer;
 

import java.util.List;

import com.haihai.picture.viewer.advertise.AbstractMobiSageMediatorAd;
import com.haihai.picture.viewer.database.PictureCategoryDao;
import com.haihai.picture.viewer.database.PictureCategoryModel;
import com.haihai.picture.viewer.util.FileDownload;
import com.haihai.picture.viewer.view.StaticPictureView;
import com.haihai.picture.viewer.view.WelcomeView;
import com.mobisage.android.agg.view.AdSageLayout;
import com.mobisage.android.agg.view.AdSageSize;


import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Welcome   extends AbstractMobiSageMediatorAd{
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.welcom_layout);
		new StaticPictureView(this);   
		  PictureCategoryDao pictureCategoryDao = new PictureCategoryDao(this);
		  for (int i = 0; i < 7; i++) {
			  PictureCategoryModel pictureCategoryModel = new PictureCategoryModel();
			  pictureCategoryModel.setName("Sexy"  );
			  pictureCategoryModel.setUrl("test" );
			  pictureCategoryDao.insert(pictureCategoryModel);
		  }
		  PictureCategoryModel pictureCategoryModel = new PictureCategoryModel();
		  pictureCategoryModel.setName("Sexy"  );
		  pictureCategoryModel.setUrl("test" );
		List<PictureCategoryModel> pictureCategoryModels = pictureCategoryDao.query(pictureCategoryModel);
		for (PictureCategoryModel pictureCategoryModel2 : pictureCategoryModels) {
			Log.w("test", "ID: " + pictureCategoryModel2.getId() +" __ Name: "+ pictureCategoryModel2.getName());
		}
		  /*String picListUrl = "http://hot.baidupcs.com/file/3608ded5e636213b5c5159b13e0b4f9e?xcode=1633cd63bdba8624979b2ba7237f37fb63783c764c03dca5&fid=255009563-250528-1176229348&time=1378306504&sign=FDTAXER-DCb740ccc5511e5e8fedcff06b081203-DEzHA6awRN%2BrvzG2AOaf%2FuBA6mY%3D&to=hb&fm=N,B,U&expires=8h&rt=pr&r=617507837&logid=1731049635&fn=piclist.txt";
		final String picURL = "http://www.baidupcs.com/thumbnail/a90305752937c735ddd5e6343a0591de?fid=255009563-250528-3395885180&time=1378303914&rt=pr&sign=FDTAR-DCb740ccc5511e5e8fedcff06b081203-jigIQkYpa%2FUTUSwdj6w7sgi9nnw%3D&expires=8h&size=c850_u580&quality=100";
		final FileDownload  fileDownload = new FileDownload();
		String urls = fileDownload.getTxtString(picListUrl) ;
		System.out.println(urls);
		final String picUrls[] = urls.split("|");
		System.out.println(picUrls);
		Log.w("txt", urls + "----" ) ;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < picUrls.length; i++){
					Log.w("txt", picUrls[i] );
					pic.setImageBitmap(fileDownload.getBitMap(pic.getContext(), picURL));
				}
			}
		}).start();
		
		
		pic.setImageBitmap(fileDownload.getBitMap(pic.getContext(), picURL));
		addADs();*/
		
	}

	@Override
	public void addADs() {
		 
	}

	/*public void addADs() {
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.fullscreen_content_controls);
		final AdSageLayout adSageLayout;
        adSageLayout = new AdSageLayout(this, AbstractMobiSageMediatorAd.yourPublishId, AdSageSize.AdSageSize_Auto);	
        adSageLayout.setAdListener(this);
        linearLayout.addView(adSageLayout);
        
	} */
	
}
