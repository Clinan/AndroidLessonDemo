package cn.edu.hdu.lessiontest11.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import cn.edu.hdu.lessiontest11.db.DbUtil;
import cn.edu.hdu.lessiontest11.db.MySQLiteHelper;

public class DataBaseContentProvider extends ContentProvider {

    //放置一个数据库
    MySQLiteHelper dbHelper;
    SQLiteDatabase db;


    //主机名
    final static String authority = "cn.edu.hdu.db.test";
    final static Uri BASE_URI = Uri.parse("content://cn.edu.hdu.db.test");

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return db.delete(DbUtil.tableName,selection,selectionArgs);
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
        long rowId = db.insert(DbUtil.tableName, null, values);
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
        dbHelper = new MySQLiteHelper(getContext(), null, 1);
        db = dbHelper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return db.query(DbUtil.tableName, projection, selection, selectionArgs, null, null, null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return db.update(DbUtil.tableName, values, selection, selectionArgs);
    }
}
