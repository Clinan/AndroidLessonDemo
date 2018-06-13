package cn.edu.hdu.lessontest13;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import cn.edu.hdu.lessontest13.db.DbHelper;
import cn.edu.hdu.lessontest13.db.MySQLiteHelper;

public class MyContentProvider extends ContentProvider {

    //放置一个数据库
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase db;


    //主机名
    final static String authority = "cn.edu.hdu.db.test";
    final static Uri BASE_URI = Uri.parse("content://cn.edu.hdu.db.test");
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return db.delete(DbHelper.tableName,selection,selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId = db.insert(DbHelper.tableName, null, values);
        if (rowId == -1) {
            //添加失败
            return null;
        } else {
            //发送内容广播
            getContext().getContentResolver().notifyChange(BASE_URI, null);
            //添加成功
            return ContentUris.withAppendedId(uri, rowId);
        }
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MySQLiteHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return db.query(DbHelper.tableName, projection, selection, selectionArgs, null, null, null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return db.update(DbHelper.tableName, values, selection, selectionArgs);
    }
}
