package com.linqinen.mho.sqlite.suitskillattribute;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.linqinen.mho.bean.SuitSkillAttribute;

import java.util.ArrayList;
import java.util.List;


//数据库的增删改查
public class SuitSkillAttributeDBhelper {

	private final String TAG = "SuitSkillDBhelper";
	private List<SuitSkillAttribute> mListCache;

	private SuitSkillAttributeSQlite sqlite;
	private SQLiteDatabase dataBase;
	public SuitSkillAttributeDBhelper(Context context, List<SuitSkillAttribute> mListCache){
		this.mListCache = mListCache;
		if (sqlite == null) {
			sqlite = new SuitSkillAttributeSQlite(context,mListCache);
		}
		dataBase = sqlite.getWritableDatabase();
		if (dataBase.isOpen()) {
			dataBase.close();
		}
	}

	public ArrayList<SuitSkillAttribute> queryAllData(){

		ArrayList<SuitSkillAttribute> mUserList = new ArrayList<>();

		//返回一个可写的数据库对象，负责增删改查
		dataBase = sqlite.getWritableDatabase();

		//查询 简单level 3*3 正常level 4*4 困难level 5*5 三个难度 的数据
		// i 代表level
		//cursor游标（迭代器）           1.表名           2.列名             3和4.需要查询的条件                          5和6条件            7排序的方式
//			Cursor cursor = dataBase.query(String table, String[] columns, String selection,String[] selectionArgs, String groupBy, String having,String orderBy);
		Cursor cursor = dataBase.query(SuitSkillAttributeSQlite.TABLE_NAME,      null,            "name=?", null,        null,           null,     "id asc");
		while (cursor.moveToNext()) {
			SuitSkillAttribute mBean = new SuitSkillAttribute();
			mBean.setName(cursor.getString(cursor.getColumnIndex("name")));
			mBean.setIsPossessHead(cursor.getInt(cursor.getColumnIndex("isPossessHead")));
			mBean.setIsPossessHead(cursor.getInt(cursor.getColumnIndex("isPossessHand")));
			mBean.setIsPossessHead(cursor.getInt(cursor.getColumnIndex("isPossessClothes")));
			mBean.setIsPossessHead(cursor.getInt(cursor.getColumnIndex("isPossessWaist")));
			mBean.setIsPossessHead(cursor.getInt(cursor.getColumnIndex("isPossessFoot")));
			mUserList.add(mBean);
		}

		cursor.close();
		dataBase.close();
		return mUserList;

	}
	public void onlyQueryAllData(){

		//返回一个可写的数据库对象，负责增删改查
		dataBase = sqlite.getWritableDatabase();

		//查询 简单level 3*3 正常level 4*4 困难level 5*5 三个难度 的数据
		// i 代表level
		//cursor游标（迭代器）           1.表名           2.列名             3和4.需要查询的条件                          5和6条件            7排序的方式
//			Cursor cursor = dataBase.query(String table, String[] columns, String selection,String[] selectionArgs, String groupBy, String having,String orderBy);
		Cursor cursor = dataBase.query(SuitSkillAttributeSQlite.TABLE_NAME,      null,            null, null,        null,           null,     null);
		while (cursor.moveToNext()) {
			SuitSkillAttribute mBean = new SuitSkillAttribute();
			mBean.setName(cursor.getString(cursor.getColumnIndex("name")));
			mBean.setIsPossessHead(cursor.getInt(cursor.getColumnIndex("isPossessHead")));
			mBean.setIsPossessHead(cursor.getInt(cursor.getColumnIndex("isPossessHand")));
			mBean.setIsPossessHead(cursor.getInt(cursor.getColumnIndex("isPossessClothes")));
			mBean.setIsPossessHead(cursor.getInt(cursor.getColumnIndex("isPossessWaist")));
			mBean.setIsPossessHead(cursor.getInt(cursor.getColumnIndex("isPossessFoot")));
			mListCache.add(mBean);
		}

		cursor.close();
		dataBase.close();

		Log.i(TAG, "onlyQueryAllData: mListCache:"+mListCache.toString());

	}

	//查询并更新数据
	public void updateData(String name,
						   int isPossessHead,int isPossessHand,int isPossessClothes,
						   int isPossessWaist,int isPossessFoot){
		dataBase = sqlite.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name+"11");
		contentValues.put("isPossessHead", isPossessHead);
		contentValues.put("isPossessHand", isPossessHand);
		contentValues.put("isPossessClothes", isPossessClothes);
		contentValues.put("isPossessWaist", isPossessWaist);
		contentValues.put("isPossessFoot",isPossessFoot);

		Log.i(TAG, "updateData: contentValues:"+contentValues.toString());
		dataBase.update(SuitSkillAttributeSQlite.TABLE_NAME, contentValues, "name=?", new String[]{name});
		dataBase.close();
	}
	/**删除数据库条目*/
	public void deleteData(String oldName){
		dataBase = sqlite.getWritableDatabase();

		dataBase.delete(SuitSkillAttributeSQlite.TABLE_NAME,  "name=?", new String[]{oldName});
		dataBase.close();
	}

	//清空存档
	public void resetData(){
		dataBase = sqlite.getWritableDatabase();
		dataBase.delete(SuitSkillAttributeSQlite.TABLE_NAME, null, null);

		dataBase.close();
	}
//	/**增加数据库条目*/
//	public void addData(String name , String num , String price){
//		dataBase = sqlite.getWritableDatabase();
//		ContentValues contentValues = new ContentValues();
//		contentValues.put("name", name);
//		contentValues.put("num", num);
//		contentValues.put("price", price);
//
//		dataBase.insert(SuitSkillAttributeSQlite.TABLE_NAME, "name", contentValues);
//		dataBase.close();
//	}
	/**增加数据库条目*/
	public void addAllData(List<SuitSkillAttribute> mListCache){
		dataBase = sqlite.getWritableDatabase();
		Log.i(TAG, "addAllData: mListCache:"+mListCache.size());

		for (int i = 0; i < mListCache.size(); i++) {
			ContentValues contentValuses = new ContentValues();
			contentValuses.put("name", mListCache.get(i).getName());
			contentValuses.put("isPossessHead", mListCache.get(i).getIsPossessHead());
			contentValuses.put("isPossessHand", mListCache.get(i).getIsPossessHand());
			contentValuses.put("isPossessClothes", mListCache.get(i).getIsPossessClothes());
			contentValuses.put("isPossessWaist", mListCache.get(i).getIsPossessWaist());
			contentValuses.put("isPossessFoot", mListCache.get(i).getIsPossessFoot());

			dataBase.insert(SuitSkillAttributeSQlite.TABLE_NAME, "name", contentValuses);
		}
		dataBase.close();
	}

	
}
