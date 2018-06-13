package cn.edu.hdu.lessiontest11;

import org.junit.Test;

import cn.edu.hdu.lessiontest11.db.DbUtil;
import cn.edu.hdu.lessiontest11.db.MySQLiteHelper;
import cn.edu.hdu.lessiontest11.db.entity.Staff;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        Staff staff = new Staff();
        staff.setAll(0, "Tom", "male", "computer", 5400);
        staff.setAll(1, "Einstein", "male", "computer", 4800);
        staff.setAll(2, "Lily", "female", "1.68", 5000);
        staff.setAll(3, "Warner", "male", null,0);
        staff.setAll(3, "Napoleon", "male", null,0);
        String[] names = DbUtil.getFiledName(staff);
        System.out.println(names.toString());
    }
}