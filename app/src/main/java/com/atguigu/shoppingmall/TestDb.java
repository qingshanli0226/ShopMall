package com.atguigu.shoppingmall;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;

public class TestDb {
    public void ge(){
        TestHelper testHelper = new TestHelper();
        SQLiteDatabase db = testHelper.getWritableDatabase();
        Notification notification = new Notification.Builder()
        NotificationManager notificationManager = new NotificationManager();
        notificationManager.notify(1,);

    }
}
