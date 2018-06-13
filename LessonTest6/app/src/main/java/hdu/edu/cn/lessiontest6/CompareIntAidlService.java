package hdu.edu.cn.lessiontest6;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class CompareIntAidlService extends Service {
    private IBinder iBinder = new ICompareIntAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong,
                               boolean aBoolean, float aFloat,
                               double aDouble, String aString)
                throws RemoteException {

        }

        @Override
        public int intCompare(int n1, int n2) throws RemoteException {
            if (n1 > n2) {
                return n1;
            } else {
                return n2;
            }
        }
    };

    public CompareIntAidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
}
