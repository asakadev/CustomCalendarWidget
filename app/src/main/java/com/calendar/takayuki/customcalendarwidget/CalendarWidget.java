package com.calendar.takayuki.customcalendarwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class CalendarWidget extends AppWidgetProvider {

    public final static String ACTION_CALENDAR_UPDATE =
            "calendar.widget.action.WIDGET_UPDATE";

    public final static String ACTION_CALENDAR_SELECT_IMAGE =
            "calendar.widget.action.WIDGET_SELECT_IMAGE";

    /**
     * 最初の１個目のAppWidgetが設置された時に呼ばれる
     */
    @Override
    public void onEnabled(Context context){
        super.onEnabled(context);
    }

    /**
     * AppWidget が設置された時/更新期間が来た時に呼ばれる
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for(int appWidgetId : appWidgetIds){
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        context.startService(createIntent(context, ACTION_CALENDAR_UPDATE));
    }

    /**
     * AppWidget が削除された時に呼ばれる
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds){
        super.onDeleted(context, appWidgetIds);
    }

    /**
     * 最後の AppWidget が削除された時に呼ばれる
     */
    @Override
    public void onDisabled(Context context){
        super.onDisabled(context);
    }

    /**
     * 上記のコールバックに振り分けるメソッド
     */
    @Override
    public void onReceive(Context context, Intent intent){
        super.onReceive(context, intent);
    }

    /**
     * AppWidget をリサイズした時に呼ばれる
     */
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
                                          int appWidgetId, Bundle newOptions){
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        updateAppWidget(context, appWidgetManager, appWidgetId);
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.calendar_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private Intent createIntent(Context context, String action) {
        Intent intent = new Intent(context, CalendarWidgetService.class);
        intent.setAction(action);
        return intent;
    }
//
//    @Override
//    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
//        }
//    }
//
//    @Override
//    public void onEnabled(Context context) {
//        // Enter relevant functionality for when the first widget is created
//    }
//
//    @Override
//    public void onDisabled(Context context) {
//        // Enter relevant functionality for when the last widget is disabled
//    }
}

