package com.linqinen.mho.sqlite.monstermaterial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.linqinen.mho.bean.MaterialPrice;

import java.util.List;


//数据库的增删改查
public class MonsterMaterialDBhelper {

	private final String TAG = "MonsterMaterialDBhelper";
	private List<MaterialPrice> mListCache;

	private MonsterMaterialSQlite sqlite;
	private SQLiteDatabase dataBase;

	public MonsterMaterialDBhelper(Context context, List<MaterialPrice> mListCache){
		this.mListCache = mListCache;
		if (sqlite == null) {
			sqlite = new MonsterMaterialSQlite(context,mListCache);
		}
		//返回一个可写的数据库对象，负责增删改查
		dataBase = sqlite.getWritableDatabase();

	}

	public void closeSQLiteDatabase(){
		if (dataBase.isOpen()) {
			dataBase.close();
		}
	}

	public Cursor cursorQuery(){
		String querySql = "select * from " + MonsterMaterialSQlite.TABLE_NAME;
		return dataBase.rawQuery(querySql, null);
	}

	public void onlyQueryAllData(){

		//cursor游标（迭代器）           1.表名                   2.列名             3和4.需要查询的条件                          5和6条件            7排序的方式
//			Cursor cursor = dataBase.query(String table,         String[] columns, String selection,String[] selectionArgs, String groupBy, String having,String orderBy);
		Cursor cursor = dataBase.query(MonsterMaterialSQlite.TABLE_NAME,      null,            null, null,        null,           null,     null);

		while (cursor.moveToNext()) {
			MaterialPrice mBean = new MaterialPrice();
			mBean.setName(cursor.getString(cursor.getColumnIndex("name")));
			mBean.setToString(cursor.getString(cursor.getColumnIndex("toString")));
			mBean.setIsRefresh(cursor.getInt(cursor.getColumnIndex("isRefresh")));
			mListCache.add(mBean);
		}

		cursor.close();

		Log.i(TAG, "onlyQueryAllData: mListCache:"+mListCache.size());

	}

	//查询并更新数据
	public void updateData(String name, String toString ){
//		SELECT * FROM rank WHERE name=土蜂幼虫
//		String sql = "select * from" + SuitSkillAttributeSQlite.TABLE_NAME;
//		dataBase.execSQL();
//		Cursor cursor = dataBase.query(SuitSkillAttributeSQlite.TABLE_NAME, null,"name="+oldName, null, null, null, null);
//		cursor.moveToLast();
//		int id = cursor.getInt(cursor.getColumnIndex("id"));
//		Log.i("id", id+"");
		
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name);
		contentValues.put("toString", toString);
		contentValues.put("isRefresh", 1);
//		cursor.close();
//		db.update("tablename",values,"name=?",new String[]{"xiadong"});
		dataBase.update(MonsterMaterialSQlite.TABLE_NAME, contentValues, "name=?", new String[]{name});
	}
	/**删除数据库条目*/
	public void deleteData(String oldName){

		dataBase.delete(MonsterMaterialSQlite.TABLE_NAME,  "name=?", new String[]{oldName});
	}

	//清空存档
	public void resetData(){
		dataBase.delete(MonsterMaterialSQlite.TABLE_NAME, null, null);
	}
	/**增加数据库条目*/
	public void addData(String name , String toString){
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name);
		contentValues.put("toString", toString);
		contentValues.put("isRefresh", 1);
		dataBase.insert(MonsterMaterialSQlite.TABLE_NAME, "name", contentValues);
	}
	/**增加数据库条目*/
	public void addAllData(){
		Log.i(TAG, "addAllData: mListCache:"+mListCache.size());
		for (int i = 0; i < mListCache.size(); i++) {

			ContentValues contentValues = new ContentValues();
			contentValues.put("name", mListCache.get(i).getName());
			contentValues.put("toString", mListCache.get(i).toString());
			contentValues.put("isRefresh", mListCache.get(i).getIsRefresh());
			dataBase.insert(MonsterMaterialSQlite.TABLE_NAME, "name", contentValues);
		}
	}

}
