package cn.edu.hdu.lessiontest10;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class TimeWidget extends AppWidgetProvider {

    private Button refreshBtn;
    private Handler handler = new Handler();
    private Runnable timeRun;
    public static final int REQUEST_CODE = 0;
    public static final int FLAG_CODE = 0;
    public static final String ACTION = "cn.edu.hdu.clan.widget";
    public static final String TAG = "TimeWidget";


    @Override
    public void onUpdate(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.time_widget);
        Intent intent = new Intent(context, WidgetService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, REQUEST_CODE, intent, FLAG_CODE);
        remoteViews.setOnClickPendingIntent(R.id.refreshBtn, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);//刷新

        timeRun = new Runnable() {
            @Override
            public void run() {
                editText(context);
                handler.postDelayed(timeRun, 60000);
            }
        };
        handler.post(timeRun);
    }

    private void editText(Context context) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.time_widget);
        rv.setTextViewText(R.id.appwidget_text, simpleDateFormat.format(date));
        AppWidgetManager am = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = am.getAppWidgetIds(new ComponentName(context, TimeWidget.class));
        am.updateAppWidget(appWidgetIds, rv);//更新 所有实例
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG, intent.getAction());

        if (intent.getAction().equals(ACTION)) {
            long time = intent.getLongExtra("time", 0);
            updateWidget(context, time);
        }
    }

    private void updateWidget(Context context, long time) {
        //RemoteViews处理异进程中的View
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.time_widget);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        rv.setTextViewText(R.id.appwidget_text, simpleDateFormat.format(time));

        AppWidgetManager am = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = am.getAppWidgetIds(new ComponentName(context, TimeWidget.class));
        am.updateAppWidget(appWidgetIds, rv);//更新 所有实例
    }
}

