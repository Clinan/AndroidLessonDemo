package hdu.edu.cn.lessiontest6;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private ICompareIntAidlInterface intAidlInterface;
    private EditText n1_et;
    private EditText n2_et;
    private int n1;
    private int n2;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            intAidlInterface = ICompareIntAidlInterface.Stub.asInterface(service);
            Log.d("mainActivity3", "service connected success");
            int max = 0;
            try {
                max = intAidlInterface.intCompare(n1, n2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "最大值为" +
                    String.valueOf(max), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("mainActivity3", "service connected fail");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        n1_et = findViewById(R.id.n1_et1);
        n2_et = findViewById(R.id.n2_et2);
    }

    public void compareBtnOnclick2(View view) {
        Log.d("mainActivity3", "compareBtnOnclick2");
        n1 = Integer.valueOf(n1_et.getText().toString());
        n2 = Integer.valueOf(n2_et.getText().toString());

        startAndBindService();

    }

    private void startAndBindService() {
        Intent service = new Intent(getApplicationContext(), CompareIntAidlService.class);
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
        startService(service);
    }
}
