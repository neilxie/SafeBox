package com.max.security;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.max.security.greendao.DaoMaster;
import com.max.security.greendao.DaoSession;
import com.max.security.greendao.FileModelsDao;
import com.max.security.injector.component.AppComponent;
import com.max.security.injector.component.DaggerAppComponent;
import com.max.security.injector.module.AppModule;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by lgp on 2015/5/24.
 */
public class App extends Application{
    private AppComponent mAppComponent;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        setupDatabase();
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

    private void setupDatabase() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "SafeBox", null);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        QueryBuilder.LOG_SQL = BuildConfig.DEBUG;
        QueryBuilder.LOG_VALUES = BuildConfig.DEBUG;
    }

    public static FileModelsDao getFileModelDao() {
        return mDaoSession.getFileModelsDao();
    }

}
