package com.linqinen.mho.sqlite.suitskillattribute;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.linqinen.mho.bean.SuitSkillAttribute;

import java.util.List;

public class SuitSkillAttributeSQlite extends SQLiteOpenHelper {

	private final String TAG = "SuitSkillSQlite";


	public static final String TABLE_NAME = "SuitSkillAttribute";
	public static int version = 1;

	List<SuitSkillAttribute> mListCache;

	public SuitSkillAttributeSQlite(Context context, String name, CursorFactory factory,
									int version) {
		super(context, TABLE_NAME+".db"/*数据库名*/, null/*factory 一般不用*/, version);
	}
	public SuitSkillAttributeSQlite(Context context, List<SuitSkillAttribute> mListCache){
		super(context, TABLE_NAME+".db", null/*factory 一般不用*/, version);
		this.mListCache = mListCache;

		
	}
	//onCreate用来创建数据库
	@Override
	public void onCreate(SQLiteDatabase dataBase) {

		Log.i(TAG, "onCreate: db:"+dataBase.toString());

		//当调用了getReadableDataBase或者getWritealbeDatabase函数就会创建数据库
		dataBase.execSQL("create table " + TABLE_NAME + "(id integer primary key autoincrement , name text ," +
				" isPossessHead integer , isPossessHand integer , isPossessClothes integer , isPossessWaist integer , isPossessFoot integer)");
		/**/
		//参数1.表名
				//参数2.指定是否可以为空（尽量不要为空）
				//参数3.要保存的数据
				//key是字段的名字，必须和建表时字段相同

		for (int i = 0; i < mListCache.size(); i++) {
			ContentValues contentValuse = new ContentValues();
			contentValuse.put("name", mListCache.get(i).getName());
			contentValuse.put("isPossessHead", mListCache.get(i).getIsPossessHead());
			contentValuse.put("isPossessHand", mListCache.get(i).getIsPossessHand());
			contentValuse.put("isPossessClothes", mListCache.get(i).getIsPossessClothes());
			contentValuse.put("isPossessWaist", mListCache.get(i).getIsPossessWaist());
			contentValuse.put("isPossessFoot", mListCache.get(i).getIsPossessFoot());

			dataBase.insert(SuitSkillAttributeSQlite.TABLE_NAME, "name", contentValuse);
		}

		Log.i(TAG, "onCreate: ");
//			db.close();//在onCreate中就不要关闭了

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
