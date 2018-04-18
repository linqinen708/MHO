package com.linqinen.mho.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lin on 2017/1/18.
 */

public class MyCursorAdapter extends CursorAdapter{


    private final String TAG = "MyCursorAdapter";

    public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public MyCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        Log.i(TAG, "newView: ");
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Log.i(TAG, "bindView: ");

    }
}
