package cn.edu.hdu.lessontest13.holder;

import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import cn.edu.hdu.lessontest13.R;

/**
 * Created by arter on 2018/6/10.
 */

public class BaseViewHolder extends AbstractViewHolder {
    private TextView textView;
    public BaseViewHolder(View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.text);
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
