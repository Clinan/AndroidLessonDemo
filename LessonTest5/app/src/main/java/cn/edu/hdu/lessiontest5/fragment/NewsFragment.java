package cn.edu.hdu.lessiontest5.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.edu.hdu.lessiontest5.R;

/**
 * Created by arter on 2018/4/20.
 */

public class NewsFragment extends BaseFragment {
    public static NewsFragment newInstance(String param1) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public NewsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        TextView tv = view.findViewById(R.id.container);
        Button sender=view.findViewById(R.id.sender);
        final EditText editText= view.findViewById(R.id.broad_et);
        sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("MyBroadcastReceiver");
                String msg=editText.getText().toString();
                if (msg.equals("")){
                    msg="广播消息为空";
                }
                intent.putExtra("msg", msg);
                //发送广播
                mActivity.sendBroadcast(intent);
            }
        });
        tv.setText(agrs1);
        return view;
    }

}
