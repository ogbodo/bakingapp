

package com.izuking.udacity.android.bakingapp;

import android.app.Application;

import com.facebook.stetho.Stetho;


public class BakingApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
