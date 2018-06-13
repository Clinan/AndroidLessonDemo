package cn.edu.hdu.lessiontest4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import cn.edu.hdu.lessiontest4.fragment.AFragment;
import cn.edu.hdu.lessiontest4.fragment.BFragment;
import cn.edu.hdu.lessiontest4.fragment.TabListener;

public class TabActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        final ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        bar.addTab(bar.newTab()
                .setText("Fragment A")
                .setTabListener(new TabListener<AFragment>(
                        this, "fa",AFragment.class)));
        bar.addTab(bar.newTab()
                .setText("Fragment B")
                .setTabListener(new TabListener<BFragment>(
                        this, "fb", BFragment.class)));
        if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }

    }


}
