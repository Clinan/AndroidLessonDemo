package cn.edu.hdu.lessiontest10;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WidgetService extends Service {
    public WidgetService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
        Log.d("WidgetService",flags+"");
        sendBroadcast(new Intent(TimeWidget.ACTION).putExtra("time", System.currentTimeMillis()));
        return Service.START_STICKY;
    }
}
