package cn.edu.hdu.lessiontest5;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import cn.edu.hdu.lessiontest5.fragment.ContactsFragment;
import cn.edu.hdu.lessiontest5.fragment.NewsFragment;
import cn.edu.hdu.lessiontest5.fragment.ZoomFragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class QQMainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private NewsFragment newsFragment;
    private ContactsFragment contactsFragment;
    private ZoomFragment zoomFragment;
    private CircleImageView circleImageView;

    private TextView news_bar_tv;
    private TextView phone_bar_tv;
    private LinearLayout new_bar_ll;
    private LinearLayout phone_bar_ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqmain);
        final int account = getIntent().getIntExtra("account",1);

        setCustomActionBar();
        /**
         * bottomNavigation 设置
         */

        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */

                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */

                .setActiveColor(R.color.loginButton) //选中颜色
                .setInActiveColor("#bbbbbb") //未选中颜色
                .setBarBackgroundColor("#FFFFFF");//导航栏背景色

        /** 添加导航按钮 */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_chat_bubble_outline_black_24dp, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.ic_person_outline_cyan_800_24dp, "联系人"))
                .addItem(new BottomNavigationItem(R.drawable.ic_star_border_white_24dp, "空间"))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise(); //initialise 一定要放在 所有设置的最后一项

        setDefaultFragment();//设置默认导航栏

        circleImageView = findViewById(R.id.profile_image_bar);
        news_bar_tv=findViewById(R.id.news_bar_tv);
        new_bar_ll=findViewById(R.id.new_bar_ll);
        phone_bar_ll=findViewById(R.id.phone_bar_ll);
        phone_bar_tv=findViewById(R.id.phone_bar_tv);
        news_bar_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        new_bar_ll.setBackground(getResources().getDrawable(R.drawable.oral_left_shape));
                        news_bar_tv.setTextColor(getResources().getColor(R.color.loginButton));
                        phone_bar_ll.setBackground(null);
                        phone_bar_tv.setTextColor(getResources().getColor(R.color.white));
                    }
                });
            }
        });

        phone_bar_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        phone_bar_ll.setBackground(getResources().getDrawable(R.drawable.oral_right_shape));
                        phone_bar_tv.setTextColor(getResources().getColor(R.color.loginButton));
                        new_bar_ll.setBackground(null);
                        news_bar_tv.setTextColor(getResources().getColor(R.color.white));
                    }
                });
            }
        });

        Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                switch (account) {
                    case 1:
                        circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
                        Log.d("runnable","1");
                        break;
                    case 2:
                        circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.king));
                        break;
                    default:
                        circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
                }
            }
        });
    }

    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        newsFragment = NewsFragment.newInstance("消息");
        newsFragment.setmActivity(this);
        transaction.replace(R.id.tb, newsFragment);
        transaction.commit();
    }

    private void setCustomActionBar() {
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View mActionBarView = LayoutInflater.from(this).inflate(R.layout.actionbar_layout, null);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setCustomView(mActionBarView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);


        }
    }

    /**
     * 设置导航选中的事件
     */
    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 2:
                if (zoomFragment == null) {
                    zoomFragment = ZoomFragment.newInstance("空间");
                    zoomFragment.setmActivity(this);

                }
                transaction.replace(R.id.tb, zoomFragment);
                break;
            case 1:
                if (contactsFragment == null) {
                    contactsFragment = ContactsFragment.newInstance("联系人");
                    contactsFragment.setmActivity(this);
                }
                transaction.replace(R.id.tb, contactsFragment);
                break;
            case 0:
                if (newsFragment == null) {
                    newsFragment = NewsFragment.newInstance("消息");
                    newsFragment.setmActivity(this);
                }
                transaction.replace(R.id.tb, newsFragment);
                break;

            default:
                break;
        }

        transaction.commit();// 事务提交
    }

    /**
     * 设置未选中Fragment 事务
     */
    @Override
    public void onTabUnselected(int position) {

    }

    /**
     * 设置释放Fragment 事务
     */
    @Override
    public void onTabReselected(int position) {

    }


    public void back_mainActivity(View view) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                QQMainActivity.this.finish();
            }
        });
    }
}
