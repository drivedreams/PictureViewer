package com.haihai.picture.viewer.database;

public class PictureInfoModel {
	public final static String ID_NAME = "id";
	public final static String URL_NAME = "pic_category_id";
	public final static String PIC_URL_NAME = "pic_url";
	
	private long id;
	private int pic_category_id;
	private String pic_url = null;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getPic_category_id() {
		return pic_category_id;
	}
	public void setPic_category_id(int pic_category_id) {
		this.pic_category_id = pic_category_id;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
}
