package com.max.security;

import android.app.Application;

import com.max.security.injector.component.AppComponent;
import com.max.security.injector.component.DaggerAppComponent;
import com.max.security.injector.module.AppModule;

/**
 * Created by lgp on 2015/5/24.
 */
public class App extends Application{
    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    private void initializeInjector() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
