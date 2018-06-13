package hdu.edu.cn.lessiontest6;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class CompareIntService extends Service {
    public CompareIntService() {
    }

    private int n1=0;
    private int n2=0;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        n1=intent.getIntExtra("n1",0);
        n2=intent.getIntExtra("n2",0);
        int max=intCompare(n1,n2);
        Toast.makeText(this,"最大值为"+String.valueOf(max),Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);

    }

//    IntCompare(Int,Int)
    public int intCompare(int n1,int n2){
        if (n1>n2){
            return n1;
        }else {
            return n2;
        }
    }
}

