package hu.an.jobfinder;

import android.app.Application;

import hu.an.jobfinder.handler.PreferencesHandler;

public class JobFinderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        PreferencesHandler.initHandler(getApplicationContext());
    }
}
