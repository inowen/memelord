package eu.inowen.app.utils;

import android.app.Application;

/**
 * Used from outside to get access to things like the resources folder and such.
 */
public class App extends Application {

    private static App instance;

    public static App getInstance() { return instance; }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

}
