package com.haihai.picture.viewer.database;

public class PictureCategoryModel { 
	private int id;
	private String name = null;
	private String url = null;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
