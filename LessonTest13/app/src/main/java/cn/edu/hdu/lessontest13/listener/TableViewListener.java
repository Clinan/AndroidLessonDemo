package cn.edu.hdu.lessontest13.listener;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;

import java.util.List;

import cn.edu.hdu.lessontest13.R;
import cn.edu.hdu.lessontest13.db.DbHelper;
import cn.edu.hdu.lessontest13.db.beam.Staff;
import cn.edu.hdu.lessontest13.util.Util;

/**
 * 表格控件
 * Created by arter on 2018/6/11.
 */

public class TableViewListener implements ITableViewListener {
    private TableView tableView;
    private static final String TAG = "TableViewListenerTAG";

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        Log.d(TAG, "column" + column + ";row" + row);
        if (column == 0) {
            tableView.setSelectedRow(row);
        }

    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, final int column, final int row) {
        Log.d(TAG, "onCellLongPressed：column" + column + ";row" + row);

        if (column == 0) {
            return;
        }
        final List<String> rowData = tableView.getAdapter().getCellRowItems(row);
        final EditText edt = new EditText(tableView.getContext());
        if (column == 4) {
            edt.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        edt.setText(rowData.get(column));
        edt.setMinLines(2);
        new AlertDialog.Builder(tableView.getContext())
                .setTitle("请输入")
                .setIcon(R.drawable.ic_mode_edit_light_green_900_24dp)
                .setView(edt)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        String value = edt.getText().toString();
                        Log.d(TAG, value);

                        Staff staff = Util.toStaff(rowData);
                        switch (column) {
                            case 1:
                                staff.setName(value);
                                break;
                            case 2:
                                staff.setSex(value);
                                break;
                            case 3:
                                staff.setDepartment(value);
                                break;
                            case 4:
                                staff.setSalary(Float.valueOf(value.trim()));
                                break;
                        }
                        if (column == 4) {
                            if (!value.contains(".")){
                                tableView.getAdapter().changeCellItem(column, row, value+".0");
                            }else {
                                tableView.getAdapter().changeCellItem(column, row, value);
                            }
                        }else {

                            tableView.getAdapter().changeCellItem(column, row, value);
                        }
                        toast("修改成功");
                        staff.update(staff.getId());

                    }
                })
                .setNegativeButton("取消", null)
                .show();

    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, final int row) {
        new AlertDialog.Builder(tableView.getContext())
                .setIcon(R.drawable.ic_delete_forever_black_24dp)
                .setMessage("确定删除该记录？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<String> rowData = tableView.getAdapter().getCellRowItems(row);
                        Staff staff = Util.toStaff(rowData);
                        DbHelper.deleterStaff(staff);
                        tableView.getAdapter().removeRow(row);
                        toast("删除成功");
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    /**
     * 吐司
     *
     * @param msg
     */
    public void toast(String msg) {
        Toast.makeText(tableView.getContext(), msg, Toast.LENGTH_SHORT).show();

    }
}
