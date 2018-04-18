package com.linqinen.mho.sqlite.normalmaterial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.linqinen.mho.bean.NormalMaterialPrice;

import java.util.ArrayList;
import java.util.List;


//数据库的增删改查
public class RankDBhelper{

	private final String TAG = "GuardStonePriceDBhelper";
	private List<NormalMaterialPrice> mListCache;

	private SQlite sqlite;
	private SQLiteDatabase dataBase;
	public RankDBhelper(Context context,List<NormalMaterialPrice> mListCache){
		this.mListCache = mListCache;
		if (sqlite == null) {
			sqlite = new SQlite(context,mListCache);
		}
		dataBase = sqlite.getWritableDatabase();
	}

	public ArrayList<NormalMaterialPrice> queryAllData(){

		ArrayList<NormalMaterialPrice> mUserList = new ArrayList<>();

		//查询 简单level 3*3 正常level 4*4 困难level 5*5 三个难度 的数据
		// i 代表level
		//cursor游标（迭代器）           1.表名           2.列名             3和4.需要查询的条件                          5和6条件            7排序的方式
//			Cursor cursor = dataBase.query(String table, String[] columns, String selection,String[] selectionArgs, String groupBy, String having,String orderBy);
		Cursor cursor = dataBase.query(SQlite.TABLE_NAME,      null,            "name=?", null,        null,           null,     "id asc");
		while (cursor.moveToNext()) {
			NormalMaterialPrice mBean = new NormalMaterialPrice();
			mBean.setName(cursor.getString(cursor.getColumnIndex("name")));
			mBean.setNum(cursor.getString(cursor.getColumnIndex("num")));
			mBean.setPrice(cursor.getString(cursor.getColumnIndex("price")));
			mUserList.add(mBean);
		}

		cursor.close();
		return mUserList;

	}

	public void closeSQLiteDatabase(){
		if (dataBase.isOpen()) {
			dataBase.close();
		}
	}

	public Cursor cursorQuery(){
		String querySql = "select * from " + SQlite.TABLE_NAME;
		return dataBase.rawQuery(querySql, null);
	}
	public void onlyQueryAllData(){

		//返回一个可写的数据库对象，负责增删改查

		//查询 简单level 3*3 正常level 4*4 困难level 5*5 三个难度 的数据
		// i 代表level
		//cursor游标（迭代器）           1.表名           2.列名             3和4.需要查询的条件                          5和6条件            7排序的方式
//			Cursor cursor = dataBase.query(String table, String[] columns, String selection,String[] selectionArgs, String groupBy, String having,String orderBy);
		Cursor cursor = dataBase.query(SQlite.TABLE_NAME,      null,            null, null,        null,           null,     null);
		while (cursor.moveToNext()) {
			NormalMaterialPrice mBean = new NormalMaterialPrice();
			mBean.setName(cursor.getString(cursor.getColumnIndex("name")));
			mBean.setNum(cursor.getString(cursor.getColumnIndex("num")));
			mBean.setPrice(cursor.getString(cursor.getColumnIndex("price")));
			mBean.setIsRefresh(cursor.getInt(cursor.getColumnIndex("isRefresh")));
			mListCache.add(mBean);
		}

		cursor.close();

		Log.i(TAG, "onlyQueryAllData: mListCache:"+mListCache.size());

	}

	//查询并更新数据
	public void updateData(String oldName,String newName , String num , String price){
//		SELECT * FROM rank WHERE name=土蜂幼虫
//		String sql = "select * from" + SuitSkillAttributeSQlite.TABLE_NAME;
//		dataBase.execSQL();
//		Cursor cursor = dataBase.query(SuitSkillAttributeSQlite.TABLE_NAME, null,"name="+oldName, null, null, null, null);
//		cursor.moveToLast();
//		int id = cursor.getInt(cursor.getColumnIndex("id"));
//		Log.i("id", id+"");
		
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", newName);
		contentValues.put("num", num);
		contentValues.put("price", price);
		contentValues.put("isRefresh", 1);
//		cursor.close();
//		db.update("tablename",values,"name=?",new String[]{"xiadong"});
		dataBase.update(SQlite.TABLE_NAME, contentValues, "name=?", new String[]{oldName});
	}
	/**删除数据库条目*/
	public void deleteData(String oldName){

		dataBase.delete(SQlite.TABLE_NAME,  "name=?", new String[]{oldName});
	}

	//清空存档
	public void resetData(){
		dataBase.delete(SQlite.TABLE_NAME, null, null);
	}
	/**增加数据库条目*/
	public void addData(String name , String num , String price){
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name);
		contentValues.put("num", num);
		contentValues.put("price", price);
		contentValues.put("isRefresh", 1);
		dataBase.insert(SQlite.TABLE_NAME, "name", contentValues);
	}
	/**增加数据库条目*/
	public void addAllData(){
		Log.i(TAG, "addAllData: mListCache:"+mListCache.size());
		for (int i = 0; i < mListCache.size(); i++) {

			ContentValues contentValues = new ContentValues();
			contentValues.put("name", mListCache.get(i).getName());
			contentValues.put("num", mListCache.get(i).getNum());
			contentValues.put("price", mListCache.get(i).getPrice());
			contentValues.put("isRefresh", mListCache.get(i).getIsRefresh());
			dataBase.insert(SQlite.TABLE_NAME, "name", contentValues);
		}
	}


}
