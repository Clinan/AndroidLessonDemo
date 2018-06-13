package cn.edu.hdu.lessontest13.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hdu.lessontest13.R;
import cn.edu.hdu.lessontest13.db.beam.Staff;
import cn.edu.hdu.lessontest13.holder.CellViewHolder;
import cn.edu.hdu.lessontest13.holder.ColumnHeaderViewHolder;
import cn.edu.hdu.lessontest13.holder.RowHeaderViewHolder;

/**
 * Created by arter on 2018/6/10.
 */

public class MyTableViewAdapter extends AbstractTableAdapter<String, String, String> {

    private static final String TAG = "MyTableViewAdapterTAG";
    private final LayoutInflater mInflater;
    private List<View> rowHeightList = new ArrayList<>();

    public MyTableViewAdapter(Context context) {
        super(context);
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout = mInflater.inflate(R.layout.tableview_cell_layout, parent, false);
        return new CellViewHolder(layout);
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {
        String str = (String) cellItemModel;
        CellViewHolder cellViewHolder = (CellViewHolder) holder;
        cellViewHolder.getTextView().setText(str);
        if (columnPosition == 0) {
            rowHeightList.add(cellViewHolder.getTextView());
        }
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout = mInflater.inflate(R.layout.tableview_column_header_layout, parent, false);
        return new ColumnHeaderViewHolder(layout);
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {
        String s = (String) columnHeaderItemModel;
        ColumnHeaderViewHolder columnHeaderViewHolder = (ColumnHeaderViewHolder) holder;
        columnHeaderViewHolder.getTextView().setText(s);
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout = mInflater.inflate(R.layout.tableview_row_header_layout, parent, false);

        return new RowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {
        String s = (String) rowHeaderItemModel;
        RowHeaderViewHolder rowHeaderViewHolder = (RowHeaderViewHolder) holder;
        rowHeaderViewHolder.getTextView().setText(s);
    }

    @Override
    public View onCreateCornerView() {
        return mInflater.inflate(R.layout.tableview_corner_layout, null,false);
    }

    private int index = 0;

    public View getCellView() {

        View view = rowHeightList.get(index % rowHeightList.size());
        index++;
        return view;
    }

}
