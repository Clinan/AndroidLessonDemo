package hdu.edu.cn.lessiontest6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private EditText n1_et;
    private EditText n2_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        n1_et = findViewById(R.id.n1_et);
        n2_et = findViewById(R.id.n2_et);
    }

    public void compareBtnOnclick(View view) {

        Intent intent = new Intent(getApplicationContext(), CompareIntService.class);
        int n1 = Integer.valueOf(n1_et.getText().toString());
        int n2 = Integer.valueOf(n2_et.getText().toString());
        intent.putExtra("n1", n1);
        intent.putExtra("n2", n2);
        startService(intent);
    }

    public void nextPageBtnOnclick(View view) {
        startActivity(new Intent(getApplicationContext(),Main3Activity.class));
    }

}
