package cn.edu.hdu.lesstiontest12;

import android.content.ContentResolver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.rmondjone.locktableview.LockTableView;
import com.rmondjone.xrecyclerview.ProgressStyle;
import com.rmondjone.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hdu.lesstiontest12.db.StaffDao;
import cn.edu.hdu.lesstiontest12.db.entity.Staff;

public class MainActivity extends AppCompatActivity {
    private LockTableView mLockTableView;
    private ContentResolver contentResolver;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout parentLayout = findViewById(R.id.parentLayout);
        ArrayList<ArrayList<String>> lists = new ArrayList<>();
        //添加表头
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("id");
        stringList.add("name");
        stringList.add("sex");
        stringList.add("department");
        stringList.add("salary");
        lists.add(stringList);
        contentResolver = getContentResolver();
        ArrayList<Staff> staffList = StaffDao.selectStaff(contentResolver);
        for (Staff s : staffList) {
            lists.add(toArrayList(s));
        }
        initTable(parentLayout,lists);
    }


    private void initTable(final ViewGroup parentLayout, final ArrayList<ArrayList<String>> mTableDatas) {
        mLockTableView = new LockTableView(this, parentLayout, mTableDatas);
        Log.e("表格加载开始", "当前线程：" + Thread.currentThread());
        mLockTableView.setLockFristColumn(false) //是否锁定第一列
                .setLockFristColumn(false) //是否锁定第一行
                .setMaxColumnWidth(100) //列最大宽度
                .setMinColumnWidth(60) //列最小宽度
                .setColumnWidth(1, 60) //设置指定列文本宽度(从0开始计算,宽度单位dp)
                .setMinRowHeight(20)//行最小高度
                .setMaxRowHeight(60)//行最大高度
                .setTextViewSize(16) //单元格字体大小
                .setCellPadding(15)//设置单元格内边距(dp)
                .setFristRowBackGroudColor(R.color.table_head)//表头背景色
                .setTableHeadTextColor(R.color.beijin)//表头字体颜色
                .setTableContentTextColor(R.color.border_color)//单元格字体颜色
                .setNullableString("N/A") //空值替换值
                .setTableViewListener(new LockTableView.OnTableViewListener() {
                    //设置横向滚动监听
                    @Override
                    public void onTableViewScrollChange(int x, int y) {
                        Log.e("滚动值", "[" + x + "]" + "[" + y + "]");
                    }
                })
                //设置横向滚动边界监听
                .setTableViewRangeListener(new LockTableView.OnTableViewRangeListener() {
                    @Override
                    public void onLeft(HorizontalScrollView horizontalScrollView) {
                        Log.e("滚动边界", "滚动到最左边");
                    }

                    @Override
                    public void onRight(HorizontalScrollView view) {
                        Log.e("滚动边界", "滚动到最右边");
                    }
                })
                .setOnLoadingListener(new LockTableView.OnLoadingListener() {
                    //下拉刷新、上拉加载监听
                    @Override
                    public void onRefresh(final XRecyclerView mXRecyclerView, final ArrayList<ArrayList<String>> mTableDatas) {
//                        Log.e("表格主视图",mXRecyclerView);
//                        Log.e("表格所有数据",mTableDatas);
                        //如需更新表格数据调用,部分刷新不会全部重绘
                        mLockTableView.setTableDatas(getData());
                        //停止刷新
                        mXRecyclerView.refreshComplete();
                    }

                    @Override
                    public void onLoadMore(final XRecyclerView mXRecyclerView, final ArrayList<ArrayList<String>> mTableDatas) {
//                        Log.e("表格主视图",mXRecyclerView);
//                        Log.e("表格所有数据",mTableDatas);
                        //如需更新表格数据调用,部分刷新不会全部重绘
                        mLockTableView.setTableDatas(mTableDatas);
                        //停止刷新
                        mXRecyclerView.loadMoreComplete();
                        //如果没有更多数据调用
                        mXRecyclerView.setNoMore(true);
                    }
                })
                .setOnItemClickListenter(new LockTableView.OnItemClickListenter() {
                    @Override
                    public void onItemClick(View item, int position) {
                        Log.e("点击事件", position + "");
                    }
                })
                .setOnItemLongClickListenter(new LockTableView.OnItemLongClickListenter() {
                    @Override
                    public void onItemLongClick(final View item, final int position) {
                        Log.e("长按事件", position + "");
                        final PopupWindow popupWindow = new PopupWindow(getApplicationContext());
                        View view = View.inflate(getApplicationContext(), R.layout.popup_layout, null);
                        popupWindow.setContentView(view);
                        final EditText idEditText = view.findViewById(R.id.id_staff_btn);
                        final EditText nameEditText = view.findViewById(R.id.name_staff_btn);
                        final EditText sexEditText = view.findViewById(R.id.sex_staff_btn);
                        final EditText deptEditText = view.findViewById(R.id.dept_staff_btn);
                        final EditText salaryEditText = view.findViewById(R.id.salary_staff_btn);
                        ArrayList currentList = getData().get(position);
                        idEditText.setText(currentList.get(0).toString());
                        nameEditText.setText(currentList.get(1).toString());
                        sexEditText.setText(currentList.get(2).toString());
                        deptEditText.setText(currentList.get(3).toString());
                        salaryEditText.setText(currentList.get(4).toString());
                        Button okBtn = view.findViewById(R.id.ok_btn);
                        okBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = Integer.valueOf(idEditText.getText().toString());
                                String name = nameEditText.getText().toString();
                                String sex = sexEditText.getText().toString();
                                String dept = deptEditText.getText().toString();
                                float salary = Float.valueOf(salaryEditText.getText().toString());

                                Staff tmp = new Staff();
                                tmp.setAll(id, name, sex, dept, salary);
                                StaffDao.updateStaff(contentResolver, tmp);
                                mLockTableView.setTableDatas(getData());
                                popupWindow.dismiss();
                                Toast.makeText(getApplicationContext(),"更新成功",Toast.LENGTH_SHORT).show();

                            }
                        });
                        Button cancelBtn = view.findViewById(R.id.cancel_btn);
                        cancelBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });
                        final Button deleteBtn = view.findViewById(R.id.delete_btn);
                        deleteBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList currentList = getData().get(position);
                                Staff staff = new Staff();
                                staff.setAll(Integer.valueOf(currentList.get(0).toString()),
                                        currentList.get(1).toString(),
                                        currentList.get(2).toString(),
                                        currentList.get(3).toString(),
                                        Float.valueOf(currentList.get(4).toString()));
                                StaffDao.deleteStaff(contentResolver, staff.getId());
                                mLockTableView.setTableDatas(getData());
                                popupWindow.dismiss();
                                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                        popupWindow.update();

                        popupWindow.setFocusable(true);
//                       popupWindow.setAnimationStyle(R.style.add_bill_popup_anim);
                        popupWindow.setWidth(1080);
                        popupWindow.setHeight(1100);
                        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00eeeeee")));
                        popupWindow.showAsDropDown(item, 0, 0, Gravity.BOTTOM);

                    }
                })
                .setOnItemSeletor(R.color.dashline_color)//设置Item被选中颜色
                .show(); //显示表格,此方法必须调用
        mLockTableView.getTableScrollView().setPullRefreshEnabled(true);
        mLockTableView.getTableScrollView().setLoadingMoreEnabled(true);
        mLockTableView.getTableScrollView().setRefreshProgressStyle(ProgressStyle.SquareSpin);
        //属性值获取
//        Log.e("每列最大宽度(dp)", mLockTableView.getColumnMaxWidths().toString());
//        Log.e("每行最大高度(dp)", mLockTableView.getRowMaxHeights().toString());
//        Log.e("表格所有的滚动视图", mLockTableView.getScrollViews().toString());
//        Log.e("表格头部固定视图(锁列)", mLockTableView.getLockHeadView().toString());
//        Log.e("表格头部固定视图(不锁列)", mLockTableView.getUnLockHeadView().toString());

    }

    private ArrayList<ArrayList<String>> getData() {
        List<Staff> staffList = StaffDao.selectStaff(contentResolver);
        ArrayList<ArrayList<String>> lists = new ArrayList<>();
        for (Staff s : staffList) {
            lists.add(toArrayList(s));
        }
        return lists;
    }

    private ArrayList<String> toArrayList(Staff staff) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(staff.getId()));
        arrayList.add(String.valueOf(staff.getName()));
        arrayList.add(String.valueOf(staff.getSex()));
        arrayList.add(String.valueOf(staff.getDepartment()));
        arrayList.add(String.valueOf(staff.getSalary()));
        return arrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "添加");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("MENU", item.getItemId() + "");
        final PopupWindow popupWindow = new PopupWindow(getApplicationContext());
        View view = View.inflate(getApplicationContext(), R.layout.popup_layout, null);
        popupWindow.setContentView(view);
        final EditText idEditText = view.findViewById(R.id.id_staff_btn);
        idEditText.setHint("ID由数据库自动生成，无需填写");
        final EditText nameEditText = view.findViewById(R.id.name_staff_btn);
        final EditText sexEditText = view.findViewById(R.id.sex_staff_btn);
        final EditText deptEditText = view.findViewById(R.id.dept_staff_btn);
        final EditText salaryEditText = view.findViewById(R.id.salary_staff_btn);
        Button okBtn = view.findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = 0;
                String name = nameEditText.getText().toString();
                String sex = sexEditText.getText().toString();
                String dept = deptEditText.getText().toString();
                float salary = Float.valueOf(salaryEditText.getText().toString());

                Staff tmp = new Staff();
                tmp.setAll(id, name, sex, dept, salary);
                StaffDao.insertStaff(contentResolver, tmp);
                mLockTableView.setTableDatas(getData());
                popupWindow.dismiss();
                Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
            }
        });
        Button cancelBtn = view.findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        Button deleteBtn = view.findViewById(R.id.delete_btn);
        deleteBtn.setVisibility(View.GONE);
        popupWindow.update();
        popupWindow.setFocusable(true);
        popupWindow.setWidth(1080);
        popupWindow.setHeight(1100);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00eeeeee")));
        popupWindow.showAsDropDown( mLockTableView.getLockHeadView(), 0, Gravity.BOTTOM);

        return true;
    }
}
