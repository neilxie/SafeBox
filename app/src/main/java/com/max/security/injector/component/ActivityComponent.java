package com.max.security.injector.component;

import android.content.Context;

import com.max.security.injector.Activity;
import com.max.security.injector.ContextLifeCycle;
import com.max.security.injector.module.ActivityModule;
import com.max.security.ui.MainActivity;

import dagger.Component;


/**
 * Created by lgp on 2015/9/2.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(MainActivity activity);
//    void inject(NoteActivity activity);
//    void inject(SettingActivity activity);
    android.app.Activity activity();
//    FinalDb finalDb();
    @ContextLifeCycle("Activity") Context activityContext();
    @ContextLifeCycle("App") Context appContext();
}
