package com.haihai.picture.viewer.staticinfo;
 

public class PhoneStaticInfo  {
	
	private static int screenWidth;
	private static int screenHeight;
	
	
	public static int getScreenWidth() {
		return screenWidth;
	}
	public static void setScreenWidth(int screenWidth) {
		PhoneStaticInfo.screenWidth = screenWidth;
	}
	public static int getScreenHeight() {
		return screenHeight;
	}
	public static void setScreenHeight(int screenHeight) {
		PhoneStaticInfo.screenHeight = screenHeight;
	}
	
}
