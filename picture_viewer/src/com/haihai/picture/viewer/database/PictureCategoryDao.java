package com.haihai.picture.viewer.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PictureCategoryDao {
	private SQLiteDatabase db = null;
	public PictureCategoryDao(Context context) {
		super(); 
		HordDBHelper hordDBHelper = new HordDBHelper(context, "PictureViewer.db", null, 1);
		db = hordDBHelper.getWritableDatabase();
	}     
	public void insert(PictureCategoryModel pictureCategoryModel) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("id", pictureCategoryModel.getId());
		contentValues.put("name", pictureCategoryModel.getName());
		contentValues.put("url", pictureCategoryModel.getUrl());
		db.insert("PictureCategory", null, contentValues );
	} 
	public void update(PictureCategoryModel target,PictureCategoryModel require) {
		PictureCategoryModel  pictureCategoryModel = (PictureCategoryModel)target;
		ContentValues contentValues = new ContentValues();
		contentValues.put("id", pictureCategoryModel.getId());
		contentValues.put("name", pictureCategoryModel.getName());
		contentValues.put("url", pictureCategoryModel.getUrl());
		db.updateWithOnConflict("PictureCategory", contentValues, null, null, SQLiteDatabase.CONFLICT_IGNORE);
		
	} 
	public List<PictureCategoryModel> query(PictureCategoryModel pictureCategoryModel) {
		List<PictureCategoryModel> categories = new ArrayList<PictureCategoryModel>(); 
		String [] requires = {"test"};
		Cursor cursor =  db.query("PictureCategory", null, "name", requires, "", "", "id", "10");
		while(cursor.moveToNext()){
			pictureCategoryModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
			pictureCategoryModel.setId(cursor.getInt(cursor.getColumnIndex("name")));
			pictureCategoryModel.setId(cursor.getInt(cursor.getColumnIndex("url")));
			categories.add(pictureCategoryModel);
		}
		return categories;
	} 
	public void delete(PictureCategoryModel pictureCategoryModel) {
		//db.delete("PictureCategory",  whereClause, whereArgs)
	} 
}
