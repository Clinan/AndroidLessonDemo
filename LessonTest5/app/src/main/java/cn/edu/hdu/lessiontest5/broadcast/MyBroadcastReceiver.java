package cn.edu.hdu.lessiontest5.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by arter on 2018/4/21.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getAction() + "\n 消息内容是：" + intent.getStringExtra("msg"),
                Toast.LENGTH_LONG).show();
    }
}
