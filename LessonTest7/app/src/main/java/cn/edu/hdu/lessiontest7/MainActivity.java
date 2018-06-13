package cn.edu.hdu.lessiontest7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //声明SharedPreferences对象
    private SharedPreferences sp;
    private EditText usernameEditText;
    private EditText pwdEditText;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        usernameEditText = findViewById(R.id.username_et);
        pwdEditText = findViewById(R.id.pwd_et);
        intent = new Intent(getApplicationContext(), Main2Activity.class);
    }


    /**
     * sharedPreferences数据存储按钮触发器
     * @param view
     */
    public void sharedPreferencesBtnOnClick(View view) {

        String username = usernameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();
        sp = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constants.USER_NAME, username);
        editor.putString(Constants.PASSWORD, pwd);
        //提交数据存入到xml文件中
        editor.apply();
        intent.putExtra(Constants.OPTION, Constants.SHARED_PREFERENCES_MODE);
        startActivity(intent);
    }

    /**
     * 内部存储按钮触发事件
     * @param view
     */
    public void internalStorageBtnOnClick(View view) {
        String username = usernameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput(Constants.FILE_NAME, MODE_PRIVATE);
            byte[] data = (username+Constants.SPLIT_SIGN+pwd).getBytes();
            try {
                // 写文件
                fileOutputStream.write(data);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "文件IO异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        intent.putExtra(Constants.OPTION,Constants.INTERNAL_STORAGE_MODE);
        startActivity(intent);
    }
}
