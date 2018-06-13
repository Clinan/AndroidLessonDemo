package cn.edu.hdu.lessontest13.db;

import android.util.Log;

import com.evrencoskun.tableview.TableView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

import java.util.List;

import cn.edu.hdu.lessontest13.db.beam.Staff;

/**
 * Created by arter on 2018/6/10.
 */

public class DbHelper {

    public static final String tableName = "staff";

    public static List<Staff> selectAllStaff() {
        List<Staff> ss = LitePal.findAll(Staff.class);
        for (Staff s : ss) {
            Log.d("DbHelper", s.toString());
        }

        return ss;

    }

    public static Staff findLast() {
        return LitePal.findLast(Staff.class);
    }

    public static void deleterStaff(Staff staff) {
        LitePal.delete(Staff.class, staff.getId());
    }

}
