package com.haihai.picture.viewer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class HordDBHelper extends SQLiteOpenHelper {
	public static final String KEY_ID ="id";
	public static final String KEY_NAME_COLUMN = "name";
	public static final String KEY_URL_COLUMN = "url"; 
	private static final String DATABASE_NAME = "PictureViewer.db";
	private static final String DATABASE_TABLE = "PictureCategory"; 
	public SQLiteDatabase db = null;
	private static final String DATABASE_CREATE = "create table " 
			+ DATABASE_TABLE +	" (" + KEY_ID + " integer primary key autoincrment, " +	KEY_NAME_COLUMN + " text not null, " 
			+ KEY_URL_COLUMN + " text not null;"; 
	public HordDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		 db = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, factory);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		 db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		onCreate(db);
	}
	
	
}
