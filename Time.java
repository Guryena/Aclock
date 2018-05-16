package com.example.kcci.aclock;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.TimerTask;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by KCCI on 2018-04-02.
 */

class Time extends TimerTask {
    RemoteViews remoteViews;
    ComponentName thisWidget;
    AppWidgetManager appWidgetManager;
    boolean _timeType = false;

    SharedPreferences sp;
    public Time(Context context, AppWidgetManager appWidgetManager) {
        this.appWidgetManager = appWidgetManager;
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.text_clock);
        thisWidget = new ComponentName(context, TextClock.class);
        sp = getDefaultSharedPreferences (context);
    }

    protected Time() {
        super();
    }

    @Override
    public void run() {
        remoteViews.setTextViewText(R.id.appwidget_text, getTime());

        appWidgetManager.updateAppWidget(thisWidget, remoteViews);
    }

    private String getTime() {
        Calendar now = Calendar.getInstance();
// 시간을 받고
        int hour;
        _timeType = sp.getBoolean("checkbox", _timeType);
        if(_timeType)
            hour = now.get(Calendar.HOUR);
        else
            hour = now.get(Calendar.HOUR_OF_DAY);
// 분 정보도 받고
        int min = now.get(Calendar.MINUTE);
// 결과를 반환해 준다.
        // 현재시간이 12시 23분 45초라면
// 12 . 23 . 45 로 될 것이다.
        return hour + " : " + min;
    }

    @Override
    public boolean cancel() {
        return super.cancel();
    }

    @Override
    public long scheduledExecutionTime() {
        return super.scheduledExecutionTime();
    }
}
