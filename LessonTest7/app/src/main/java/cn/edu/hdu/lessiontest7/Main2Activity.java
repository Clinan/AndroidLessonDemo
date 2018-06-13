package cn.edu.hdu.lessiontest7;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hdu.lessiontest7.entity.People;

public class Main2Activity extends AppCompatActivity {

    private TextView pwdTextView;
    private TextView usernameTextView;
    private String username = "";
    private String pwd = "";

    private TextView xml_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        int option = getIntent().getIntExtra(Constants.OPTION, Constants.SHARED_PREFERENCES_MODE);
        pwdTextView = findViewById(R.id.pwd_tv);
        usernameTextView = findViewById(R.id.username_tv);
        xml_tv = findViewById(R.id.xml_tv);

        //判断前一个activity点击的是哪个按钮，应采取何种方式读取username、pwd
        switch (option) {
            //sharedPreferences方式
            case Constants.SHARED_PREFERENCES_MODE:
                SharedPreferences pref = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE);
                username = pref.getString(Constants.USER_NAME, "");//第二个参数为默认值
                pwd = pref.getString(Constants.PASSWORD, "");//第二个参数为默认值
                break;
            //内部存储的方式
            case Constants.INTERNAL_STORAGE_MODE:
                FileInputStream inputStream = null;
                try {
                    inputStream = openFileInput(Constants.FILE_NAME);
                    // 获取文件内容长度
                    int fileLen = inputStream.available();
                    // 读取内容到buffer
                    byte[] buffer = new byte[fileLen];
                    inputStream.read(buffer);
                    String text = new String(buffer);
                    String array[] = text.split(Constants.SPLIT_SIGN);
                    if (array.length >= 2) {
                        username = array[0];
                        pwd = array[1];
                    }
                    if (array.length >= 1) {
                        username = array[0];
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        pwdTextView.setText(pwd);
        usernameTextView.setText(username);
    }

    /**
     * 读取XML文件按钮点击事件
     *
     * @param view
     */
    public void readXMLBtnOnClick(View view) {
        XmlPullParser xmlPullParser = getResources().getXml(R.xml.people);
        List<People> peopleList = new ArrayList<>();
        try {
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                String peopleName = xmlPullParser.getName();

                if ((peopleName != null) && peopleName.equals("person")) {
                    People people = new People();
                    int count = xmlPullParser.getAttributeCount();
                    //把属性取出来
                    for (int i = 0; i < count; i++) {
                        String attrName = xmlPullParser.getAttributeName(i);
                        String attrValue = xmlPullParser.getAttributeValue(i);
                        if ((attrName != null) && attrName.equals("name")) {
                            people.setName(attrValue);
                        } else if ((attrName != null) && attrName.equals("age")) {
                            people.setAge(Integer.valueOf(attrValue));
                        } else if ((attrName != null) && attrName.equals("id")) {
                            people.setId(Integer.valueOf(attrValue));
                        }
                    }
                    peopleList.add(people);
                }

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer peopleText = new StringBuffer("");
        for (People p : peopleList) {
            if (p.getAge() != 0 && p.getName() != null && p.getId() != 0) {
                peopleText.append("id:" + String.valueOf(p.getId()));
                peopleText.append("\tname:" + p.getName());
                peopleText.append("\tage:" + String.valueOf(p.getAge()));
                peopleText.append("\n");
            }

        }
        final String finalPeopleText = peopleText.substring(0, peopleText.length() - 2);
        xml_tv.setText(finalPeopleText);

    }


    /**
     * 读取TXT文件按钮点击事件
     *
     * @param view
     */
    public void readTXTBtnOnClick(View view) {
        TextView txt_tv = findViewById(R.id.txt_tv);
        InputStream inputStream = null;
        try {
            //获取字节流
            inputStream = getResources().openRawResource(R.raw.student);
            //按字节流的大小实例化空字节数组
            byte[] reader = new byte[inputStream.available()];
            //从流读到数组中
            inputStream.read(reader);
            txt_tv.setText(new String(reader, "utf-8")); //字节数组转为字符
        } catch (IOException e) {
            Log.e("ResourceFileDemo", e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
