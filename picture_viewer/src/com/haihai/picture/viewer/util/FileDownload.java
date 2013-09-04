package com.haihai.picture.viewer.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.haihai.picture.viewer.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class FileDownload {
	 

	public synchronized Bitmap getBitMap(Context c, String url) {
		 URL myFileUrl = null;
		 Bitmap bitmap = null;
		 try {
		 		myFileUrl = new URL(url);
		 		Log.w("Pic", "set url ...");
		 } catch (MalformedURLException e) {
			 
			 bitmap = BitmapFactory.decodeResource(c.getResources(), R.drawable.sexymm04);
			 Log.w("Pic", "failed at begin ...");
			 return bitmap;
		 }
		 try {
			 Log.w("Pic", "Begin to conn url ...");
			 HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			 conn.setDoInput(true);
			 conn.connect();
			 Log.w("Pic", "Conn success ...");
			 InputStream is = conn.getInputStream();
			 Log.w("Pic", "Beging download url ...");
			 int length = (int) conn.getContentLength();
				if (length != -1) {
				    byte[] imgData = new byte[length];
				    byte[] temp = new byte[512];
				    int readLen = 0;
				    int destPos = 0;
				    while ((readLen = is.read(temp)) > 0) {
					    System.arraycopy(temp, 0, imgData, destPos, readLen);
					    destPos += readLen;
				    }
				    
				      bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
				      }
			 } catch (IOException e) {
				 Log.w("Pic", "Download failed  ");
				 bitmap = BitmapFactory.decodeResource(c.getResources(), R.drawable.sexymm05);
				 return bitmap;
			 }
		 Log.w("Pic", "Download successfully  ");
		 return bitmap;
	}
	public synchronized String getTxtString( String url) {
		 URL myFileUrl = null; 
		 String txtString = "";
		 try {
		 		myFileUrl = new URL(url);
		 		Log.w("Txt", "set url ...");
		 } catch (MalformedURLException e) {
			 
			 Log.w("Txt", "failed at begin ...");
			 return txtString;
		 }
		 try {
			 Log.w("Txt", "Begin to conn url ...");
			 HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			 conn.setDoInput(true);
			 conn.connect();
			 Log.w("Txt", "Conn success ...");
			 InputStream is = conn.getInputStream();
			 Log.w("Txt", "Beging download url ...");
			 int length = (int) conn.getContentLength();
				if (length != -1) {
				    byte[] stringData = new byte[length];
				    byte[] temp = new byte[512];
				    int readLen = 0;
				    int destPos = 0;
					    while ((readLen = is.read(temp)) > 0) {
						    System.arraycopy(temp, 0, stringData, destPos, readLen);
						    destPos += readLen;
					    }
					    is=  new ByteArrayInputStream(stringData);
					    InputStreamReader read = new InputStreamReader( is ,"UTF-8");
					    BufferedReader bufferedReader = new BufferedReader(read);
					    String  tempString = null;
					    while (( tempString = bufferedReader.readLine()) != null) {
					    	txtString += tempString;
			            }
					    
				     }
			 } catch (IOException e) {
				 Log.w("Pic", "Download failed  ");
				 return txtString;
			 }
		 Log.w("Txt", "Download successfully  ");
		 return txtString;
	}
}
