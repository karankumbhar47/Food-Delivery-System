package com.example.swiggy_lite.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SaveDataService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("myTag", "Background service is running");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d("myTag", "save from back");
        System.out.println("onTaskRemoved called");
        super.onTaskRemoved(rootIntent);
        this.stopSelf();
    }
}
