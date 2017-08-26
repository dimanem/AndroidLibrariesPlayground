package com.dimanemets.playground.libsplayground;

import android.app.Application;


/**
 * Created by dimanemets on 19/08/2017.
 */

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .aPIModule(new APIModule("https://api.github.com"))
                    .build();
        }

        return applicationComponent;
    }
}
