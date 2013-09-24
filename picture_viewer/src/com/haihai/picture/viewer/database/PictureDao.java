package com.haihai.picture.viewer.database;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class PictureDao {
	private SQLiteDatabase db = null;
	public  void initDatabase(Context context){
		HordDBHelper hordDBHelper = new HordDBHelper(context, "PictureViewer.db", null, 1);
		db = hordDBHelper.getWritableDatabase();
	}
	public abstract void insert(Object object);
	public abstract void update(Object target,Object require);
	public abstract List<Object> query(Object object);
	public abstract void delete(Object object);
}
