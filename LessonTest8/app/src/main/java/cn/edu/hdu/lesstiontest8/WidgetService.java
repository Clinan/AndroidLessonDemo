package cn.edu.hdu.lesstiontest8;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class WidgetService extends Service {
    public WidgetService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
        sendBroadcast(new Intent("com.stone.action.start").putExtra("time", System.currentTimeMillis()));
        return Service.START_STICKY;
    }
}
