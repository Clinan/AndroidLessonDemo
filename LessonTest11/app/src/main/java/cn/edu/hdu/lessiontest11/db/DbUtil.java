package cn.edu.hdu.lessiontest11.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hdu.lessiontest11.db.entity.BaseEntity;
import cn.edu.hdu.lessiontest11.db.entity.Staff;

/**
 * 数据库工具类
 * Created by arter on 2018/6/10.
 */

public class DbUtil {
    public static final String tableName = "staff";

    /**
     * 获取属性名数组
     */
    public static String[] getFiledName(BaseEntity o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length - 2];
        for (int i = 0; i < fields.length - 2; i++) {
            System.out.println(fields[i].getName());
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /* 根据属性名获取属性值
    * */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method getterMethod = o.getClass().getMethod(getter, new Class[]{});

            String setter = "set" + firstLetter + fieldName.substring(1);
            Method setterMethod = o.getClass().getMethod(setter, new Class[]{});
            Object value = getterMethod.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteStaff(SQLiteDatabase db, Staff staff) {
        db.delete(tableName, "id = ?", new String[]{String.valueOf(staff.getId())});
    }

    public static void insertStaff(SQLiteDatabase db, Staff staff) {
        ContentValues values = new ContentValues();
//        String[] keys = getFiledName(staff);
//        for (String key : keys) {
//            values.put(key, (String) getFieldValueByName(key, staff));
//        }
        values.put("name", staff.getName());
        values.put("sex", staff.getSex());
        values.put("department", staff.getDepartment());
        values.put("salary", staff.getSalary());
        db.insert(tableName, null, values);
    }

    public static void updateStaff(SQLiteDatabase db, Staff staff) {
        ContentValues values = new ContentValues();
//        String[] keys = getFiledName(staff);
//        for (String key : keys) {
//            values.put(key, (String) getFieldValueByName(key, staff));
//        }
        values.put("id", staff.getId());
        values.put("name", staff.getName());
        values.put("sex", staff.getSex());
        values.put("department", staff.getDepartment());
        values.put("salary", staff.getSalary());
        db.update(tableName, values, "id = ?", new String[]{String.valueOf(staff.getId())});
    }

    public static List<Staff> selectAllStaff(SQLiteDatabase db) {
        List<Staff> staffList = new ArrayList<>();
        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                String dept = cursor.getString(cursor.getColumnIndex("department"));
                Float salary = cursor.getFloat(cursor.getColumnIndex("salary"));
                Staff staff = new Staff();
                staff.setAll(id, name, sex, dept, salary);
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        return staffList;
    }


}
