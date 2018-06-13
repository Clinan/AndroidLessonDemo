package cn.edu.hdu.lessiontest7.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * student表SQLiteOpenHelper
 * Created by arter on 2018/5/11.
 */

public class StudentDatabaseHelper extends SQLiteOpenHelper {

    private final static String CREATE_TABLE_SQL="CREATE TABLE student( id integer primary key autoincrement, number integer , name text,age integer)";

    public StudentDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
