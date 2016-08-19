package com.max.security.injector.component;

import android.content.Context;

import com.max.security.injector.ContextLifeCycle;
import com.max.security.injector.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lgp on 2015/9/2.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
//    App app();
    @ContextLifeCycle("App") Context context();
//    FinalDb finalDb();
//    FinalDb.DaoConfig daoConfig();
}
