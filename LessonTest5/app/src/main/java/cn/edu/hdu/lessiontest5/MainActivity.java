package cn.edu.hdu.lessiontest5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText pwd_et;
    private EditText account_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pwd_et = findViewById(R.id.password);
        account_et = findViewById(R.id.account);

    }

    public void login_btn(View view) {
        String pwd = pwd_et.getText().toString();
        String account = account_et.getText().toString();
        Intent intent = new Intent("cn.edu.lessiontest5.QQMainActivity");
        if (pwd.equals("1") && account.equals("15222")) {
            intent.putExtra("account", 1);
            startActivity(intent);
        }
        if (pwd.equals("1") && account.equals("15222555")) {
            intent.putExtra("account", 2);
            startActivity(intent);
        }

    }
}
