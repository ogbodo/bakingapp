

package com.izuking.udacity.android.bakingapp.UIs;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.izuking.udacity.android.bakingapp.data.RecipeProvider;

import com.izuking.udacity.android.bakingapp.R;

public class RecipeAppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // update all widgets
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_app_widget);

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);
            views.setRemoteAdapter(R.id.lv_ingredients, new Intent(context, RecipeWidgetRemoteViewsService.class));


            PendingIntent clickPendingIntentTemplate = TaskStackBuilder.create(context)
                    .addNextIntentWithParentStack(intent)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.lv_ingredients, clickPendingIntentTemplate);
            views.setEmptyView(R.id.lv_ingredients, R.id.widget_empty);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (RecipeProvider.ACTION_DATA_UPDATED.equals(intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                    new ComponentName(context, getClass()));
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.lv_ingredients);
        }
    }
}

