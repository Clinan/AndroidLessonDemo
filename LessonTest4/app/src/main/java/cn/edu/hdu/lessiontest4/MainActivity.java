package cn.edu.hdu.lessiontest4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnOnclick(View view) {
        startActivity(new Intent(getApplicationContext(),MenuActivity.class));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(getApplicationContext(),"触摸屏幕位置为：（"+(int)event.getRawX()+","+(int)event.getRawY()+"）",Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }
}
