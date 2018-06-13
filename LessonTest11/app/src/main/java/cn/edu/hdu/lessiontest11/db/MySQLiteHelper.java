package cn.edu.hdu.lessiontest11.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.edu.hdu.lessiontest11.db.entity.BaseEntity;

/**
 * 数据库操作类
 * Created by arter on 2018/6/9.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String dbName = "test.db";

    public MySQLiteHelper(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
    }

    public static final String CREATE_NEWS = "create table staff ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "sex text, "
            + "department text,"
            + "salary real)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
