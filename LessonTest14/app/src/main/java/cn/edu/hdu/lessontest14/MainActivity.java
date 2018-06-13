package cn.edu.hdu.lessontest14;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.adapter.AdapterDataSetChangedListener;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hdu.lessontest14.adapter.MyTableViewAdapter;
import cn.edu.hdu.lessontest14.db.beam.Staff;
import cn.edu.hdu.lessontest14.util.Util;
import cn.edu.hdu.lessontest14.listener.TableViewListener;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivityTAG";
    private TableView tableView;
    private MyTableViewAdapter tableViewAdapter;
    private TableViewListener tableViewListener;
    private ProgressBar mLoadingProgressBar;
    private TextView showMsg_tv;
    private List<Staff> staffList = null;
    List<String> columnList = new ArrayList<>();

    private ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableView = findViewById(R.id.content_container);
        mLoadingProgressBar = findViewById(R.id.loading_pb);
        showMsg_tv = findViewById(R.id.showMsg_tv);

//        tableView=new TableView(this);
        tableViewAdapter = new MyTableViewAdapter(this);
        tableView.setAdapter(tableViewAdapter);
        columnList.add("ID");
        columnList.add("姓名");
        columnList.add("性别");
        columnList.add("部门");
        columnList.add("工资");

        showProgressBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_normal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.option_create_database:
                contentResolver = getContentResolver();
                List<List<String>> lists = new ArrayList<>();
                List<String> rowList = new ArrayList<>();
                staffList = StaffDAO.selectStaff(contentResolver);
                for (int i = 0; i < staffList.size(); i++) {
                    lists.add(Util.toArrayList(staffList.get(i)));
                    rowList.add(String.valueOf(i));
                }


                tableViewAdapter.setAllItems(columnList, rowList, lists);

                tableViewListener = new TableViewListener(this);
                tableViewListener.setTableView(tableView);
                tableView.setTableViewListener(tableViewListener);
                hideProgressBar();
                myToast("创建数据库成功");
                break;
            case R.id.add_data:
                if (!isConnectDataBase()) {
                    return true;
                }

                View addView = View.inflate(this, R.layout.staff_add_layout, null);
                final EditText name_et = addView.findViewById(R.id.name_et);
                final EditText sex_et = addView.findViewById(R.id.sex_et);
                final EditText dept_et = addView.findViewById(R.id.dept_et);
                final EditText salary_et = addView.findViewById(R.id.salary_et);


                new AlertDialog.Builder(this)
                        .setTitle("请输入")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(addView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Staff s = new Staff();
                                s.setName(name_et.getText().toString());
                                s.setSex(sex_et.getText().toString());
                                s.setDepartment(dept_et.getText().toString());
                                s.setSalary(Float.valueOf(salary_et.getText().toString().trim()));
                                StaffDAO.insertStaff(contentResolver,s);
                                tableView.getAdapter().addRow(staffList.size(), "" + staffList.size(), Util.toArrayList(s));
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        staffList = StaffDAO.selectStaff(contentResolver);
                                    }
                                }).start();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                myToast("添加成功");
                break;
            case R.id.refresh:
                if (!isConnectDataBase()) {
                    return true;
                }
                staffList = StaffDAO.selectStaff(contentResolver);
                List<List<String>> cellList = new ArrayList<>();
                List<String> rowList1 = new ArrayList<>();

                for (int i = 0; i < staffList.size(); i++) {
                    Staff s = staffList.get(i);
                    cellList.add(Util.toArrayList(s));
                    rowList1.add("" + i);
                }
                tableView.setAdapter(new MyTableViewAdapter(MainActivity.this));
                tableView.getAdapter().setAllItems(columnList, rowList1, cellList);
                myToast("刷新成功");
                break;

        }
        return true;
    }

    public void myToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public boolean isConnectDataBase() {
        if (contentResolver == null) {
            myToast("数据库未连接");
            return false;
        } else {
            return true;
        }
    }

    private void showProgressBar() {
        mLoadingProgressBar.setVisibility(View.VISIBLE);
        tableView.setVisibility(View.INVISIBLE);

    }

    private void hideProgressBar() {
        mLoadingProgressBar.setVisibility(View.INVISIBLE);
        showMsg_tv.setVisibility(View.INVISIBLE);
        tableView.setVisibility(View.VISIBLE);
    }
}
