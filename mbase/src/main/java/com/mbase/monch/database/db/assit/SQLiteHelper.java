package com.mbase.monch.database.db.assit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite辅助类
 * 
 * @author mty
 * @date 2013-6-2下午4:42:47
 */
public class SQLiteHelper extends SQLiteOpenHelper {

	public interface OnDBUpgradeListener {
		void onUpdate(SQLiteDatabase db, int oldVersion, int newVersion);
	}

	private OnDBUpgradeListener onUpdateListener;

	public SQLiteHelper(Context context, String name, CursorFactory factory, int version,
						OnDBUpgradeListener onUpdateListener) {
		super(context, name, factory, version);
		this.onUpdateListener = onUpdateListener;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (onUpdateListener != null) {
			onUpdateListener.onUpdate(db, oldVersion, newVersion);
		}
	}

}