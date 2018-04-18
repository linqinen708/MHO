package com.linqinen.mho.sqlite.guardstoneprice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.linqinen.mho.bean.GuardStonePrice;

import java.util.List;


//数据库的增删改查
public class GuardStonePriceDBhelper {

	private final String TAG = "GuardStonePriceDBhelper";
	private List<GuardStonePrice> mListCache;

	private GuardStonePriceSQlite sqlite;
	private SQLiteDatabase dataBase;
	public GuardStonePriceDBhelper(Context context, List<GuardStonePrice> mListCache){
		this.mListCache = mListCache;
		if (sqlite == null) {
			sqlite = new GuardStonePriceSQlite(context,mListCache);
		}
		dataBase = sqlite.getWritableDatabase();
		if (dataBase.isOpen()) {
			dataBase.close();
		}
	}


	public void onlyQueryAllData(){

		//返回一个可写的数据库对象，负责增删改查
		dataBase = sqlite.getWritableDatabase();

		//查询 简单level 3*3 正常level 4*4 困难level 5*5 三个难度 的数据
		// i 代表level
		//cursor游标（迭代器）           1.表名           2.列名             3和4.需要查询的条件                          5和6条件            7排序的方式
//			Cursor cursor = dataBase.query(String table, String[] columns, String selection,String[] selectionArgs, String groupBy, String having,String orderBy);
		Cursor cursor = dataBase.query(GuardStonePriceSQlite.TABLE_NAME,      null,            null, null,        null,           null,     null);
		while (cursor.moveToNext()) {
			GuardStonePrice mBean = new GuardStonePrice();
			mBean.setName(cursor.getString(cursor.getColumnIndex("name")));
			mBean.setPrecisionCasting(cursor.getString(cursor.getColumnIndex("precisionCasting")));
			mBean.setAuctionPrice(cursor.getString(cursor.getColumnIndex("auctionPrice")));
			mBean.setFixedPrice(cursor.getString(cursor.getColumnIndex("fixedPrice")));
			mBean.setIsRefresh(cursor.getInt(cursor.getColumnIndex("isRefresh")));
			mListCache.add(mBean);
		}

		cursor.close();
		dataBase.close();

		Log.i(TAG, "onlyQueryAllData: mListCache:"+mListCache.size());

	}

	//查询并更新数据
	public void updateData(String name , String precisionCasting , String auctionPrice , String fixedPrice){
		dataBase = sqlite.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name);
		contentValues.put("precisionCasting", precisionCasting);
		contentValues.put("auctionPrice", auctionPrice);
		contentValues.put("fixedPrice", fixedPrice);
		contentValues.put("isRefresh", 1);
//		cursor.close();
//		db.update("tablename",values,"name=?",new String[]{"xiadong"});
		dataBase.update(GuardStonePriceSQlite.TABLE_NAME, contentValues, "name=?", new String[]{name});
		dataBase.close();
	}
	/**删除数据库条目*/
	public void deleteData(String oldName){
		dataBase = sqlite.getWritableDatabase();

		dataBase.delete(GuardStonePriceSQlite.TABLE_NAME,  "name=?", new String[]{oldName});
		dataBase.close();
	}

	//清空存档
	public void resetData(){
		dataBase = sqlite.getWritableDatabase();
		dataBase.delete(GuardStonePriceSQlite.TABLE_NAME, null, null);

		dataBase.close();
	}
	/**增加数据库条目*/
	public void addData(String name , String precisionCasting , String auctionPrice , String fixedPrice){
		dataBase = sqlite.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name);
		contentValues.put("precisionCasting", precisionCasting);
		contentValues.put("auctionPrice", auctionPrice);
		contentValues.put("fixedPrice", fixedPrice);
		contentValues.put("isRefresh", 1);
		dataBase.insert(GuardStonePriceSQlite.TABLE_NAME, "name", contentValues);
		dataBase.close();
	}
	/**增加数据库条目*/
	public void addAllData(){
		dataBase = sqlite.getWritableDatabase();
		Log.i(TAG, "addAllData: mListCache:"+mListCache.size());
		for (int i = 0; i < mListCache.size(); i++) {

			ContentValues contentValues = new ContentValues();
			contentValues.put("name", mListCache.get(i).getName());
			contentValues.put("precisionCasting", mListCache.get(i).getPrecisionCasting());
			contentValues.put("auctionPrice", mListCache.get(i).getAuctionPrice());
			contentValues.put("fixedPrice", mListCache.get(i).getFixedPrice());
			contentValues.put("isRefresh", mListCache.get(i).getIsRefresh());
			dataBase.insert(GuardStonePriceSQlite.TABLE_NAME, "name", contentValues);
		}
		dataBase.close();
	}


}
