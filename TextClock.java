package com.example.kcci.aclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.content.ComponentName;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.Calendar;
import java.util.TimerTask;
import java.util.Timer;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Implementation of App Widget functionality.
 */
public class TextClock extends AppWidgetProvider {

    static final String ACTION_CLICK = "CLICK";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        // CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.text_clock);
        //    views.setTextViewText(R.id.appwidget_text, widgetText);
        // views.setImageViewResource(R.id.imageButton4, R.drawable.cat_standing);
        Intent intent = new Intent(context, TextClock.class);
        intent.setAction(ACTION_CLICK);

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent pending = PendingIntent.getBroadcast(context, appWidgetId,
                intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pending);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    Timer timer = new Timer();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        timer.scheduleAtFixedRate(new Time(context, appWidgetManager), 1, 1000);

        test(context);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    void test(Context context) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.text_clock);
        ImageView rabbit = (ImageView) views.apply(context, null).findViewById(R.id.imageView4);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(rabbit);
        Glide.with(context).load(R.drawable.cat_standing).into(gifImage);


    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // RENEW
        if (action != null && action.equals(ACTION_CLICK)) {
            int id = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            Intent settingstart = new Intent(context, SettingsActivity.class);
            context.startActivity(settingstart);
            updateAppWidget(context, AppWidgetManager.getInstance(context), id);   // 버튼이 클릭되면 새로고침 수행
            //Log.d("ExampleWidget", "onReceive: CLICK Button");
            return;
        }

        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);

    }
}

