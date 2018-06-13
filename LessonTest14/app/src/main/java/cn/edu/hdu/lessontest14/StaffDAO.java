package cn.edu.hdu.lessontest14;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import cn.edu.hdu.lessontest14.db.beam.Staff;

/**
 * Created by arter on 2018/6/12.
 */

public class StaffDAO{

    private static final String TAG = "StaffDaoData";

    public static final Uri ContentUri = Uri.parse("content://cn.edu.hdu.clan.db.test");

    public static void deleteStaff(ContentResolver resolver, int id) {
        resolver.delete(ContentUri, "id = ?", new String[]{String.valueOf(id)});
    }

    public static void insertStaff(ContentResolver resolver, Staff staff) {
        ContentValues values = new ContentValues();
        values.put("name", staff.getName());
        values.put("sex", staff.getSex());
        values.put("department", staff.getDepartment());
        values.put("salary", staff.getSalary());
        resolver.insert(ContentUri, values);
    }

    public static void updateStaff(ContentResolver resolver, Staff staff) {
        ContentValues values = new ContentValues();
        values.put("name", staff.getName());
        values.put("sex", staff.getSex());
        values.put("department", staff.getDepartment());
        values.put("salary", staff.getSalary());
        resolver.insert(ContentUri, values);
    }

    public static ArrayList<Staff> selectStaff(@NonNull ContentResolver resolver) {
        Log.d(TAG, "当前线程：" + Thread.currentThread());
        ArrayList<Staff> staffArrayList = new ArrayList<>();
        Cursor cursor = resolver.query(ContentUri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                String dept = cursor.getString(cursor.getColumnIndex("department"));
                Float salary = cursor.getFloat(cursor.getColumnIndex("salary"));
                Staff staff = new Staff(id, name, sex, dept, salary);
                Log.d(TAG, staff.toString());
                staffArrayList.add(staff);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return staffArrayList;
    }
}
