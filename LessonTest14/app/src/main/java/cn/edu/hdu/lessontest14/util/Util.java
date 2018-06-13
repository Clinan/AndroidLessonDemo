package cn.edu.hdu.lessontest14.util;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hdu.lessontest14.db.beam.Staff;

/**
 * Created by arter on 2018/6/10.
 */

public class Util {
    public static List<String> toArrayList(Staff staff) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(staff.getId()));
        arrayList.add(String.valueOf(staff.getName()));
        arrayList.add(String.valueOf(staff.getSex()));
        arrayList.add(String.valueOf(staff.getDepartment()));
        arrayList.add(String.valueOf(staff.getSalary()));
        return arrayList;
    }

    public static Staff toStaff(List<String> strings) {
        if (strings.size() != 5) {
            throw new RuntimeException("list 长度不正确 不能正确解析");

        }
        return new Staff(Integer.valueOf(strings.get(0).trim()),strings.get(1).trim(),strings.get(2).trim(),strings.get(3).trim(),Float.valueOf(strings.get(4).trim()));

    }

}
