package cn.edu.hdu.lessiontest4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        textView2 = findViewById(R.id.textView2);
        textView2.setOnCreateContextMenuListener(this);//给组件注册Context菜单
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.showContextMenu();

            }
        });
    }

    final static int Menu_0 = Menu.FIRST;
    final static int Menu_1 = Menu.FIRST + 1;
    final static int Menu_2 = Menu.FIRST + 2;
    final static int Menu_3 = Menu.FIRST + 3;

    private TextView textView2;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d("tag", String.valueOf(item.getItemId()));
        Toast.makeText(this, "上下文菜单" + String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);

        menu.add(0, Menu_0, 0, "设置0");
        menu.add(1, Menu_1, 1, "设置1");
        menu.add(2, Menu_2, 2, "设置2");
        menu.add(3, Menu_3, 3, "设置3");
        SubMenu subMenu = menu.addSubMenu(3, 4, 4, "ssss");
        subMenu.add(3, 4, 0, "s1");
        subMenu.add(3, 5, 1, "s1");
        return true;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("快捷菜单标题");
        menu.add(0, 0, 0, "快捷菜单1");
        menu.add(0, 1, 1, "快捷菜单2");
        menu.add(0, 2, 2, "快捷菜单3");


    }

    public void btn_tab(View view) {
        startActivity(new Intent(getApplicationContext(), TabActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //响应点击菜单
        Log.d("tag", String.valueOf(item.getItemId()));
        Toast.makeText(this, "菜单" + String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }


}
