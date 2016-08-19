package com.max.security.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.max.security.injector.ContextLifeCycle;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by lgp on 2014/10/30.
 */
public class PreferenceUtils {

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor shareEditor;

    private static PreferenceUtils preferenceUtils = null;

    public static final String NOTE_TYPE_KEY = "NOTE_TYPE_KEY";

    public static final String EVERNOTE_ACCOUNT_KEY = "EVERNOTE_ACCOUNT_KEY";

    public static final String EVERNOTE_NOTEBOOK_GUID_KEY = "EVERNOTE_NOTEBOOK_GUID_KEY";

    private static final String PREFERENCE_FILE_NAME = "com.max.prf";

    @Inject @Singleton
    protected PreferenceUtils(@ContextLifeCycle("App") Context context){
        sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        shareEditor = sharedPreferences.edit();
    }

    public static PreferenceUtils getInstance(Context context){
        if (preferenceUtils == null) {
            synchronized (PreferenceUtils.class) {
                if (preferenceUtils == null) {
                    preferenceUtils = new PreferenceUtils(context.getApplicationContext());
                }
            }
        }
        return preferenceUtils;
    }

    public String getStringParam(String key){
        return getStringParam(key, "");
    }

    public String getStringParam(String key, String defaultString){
        return sharedPreferences.getString(key, defaultString);
    }

    public void saveParam(String key, String value)
    {
        shareEditor.putString(key,value).commit();
    }

    public boolean getBooleanParam(String key){
        return getBooleanParam(key, false);
    }

    public boolean getBooleanParam(String key, boolean defaultBool){
        return sharedPreferences.getBoolean(key, defaultBool);
    }

    public void saveParam(String key, boolean value){
        shareEditor.putBoolean(key, value).commit();
    }

    public int getIntParam(String key){
        return getIntParam(key, 0);
    }

    public int getIntParam(String key, int defaultInt){
        return sharedPreferences.getInt(key, defaultInt);
    }

    public void saveParam(String key, int value){
        shareEditor.putInt(key, value).commit();
    }

    public long getLongParam(String key){
        return getLongParam(key, 0);
    }

    public long getLongParam(String key, long defaultInt){
        return sharedPreferences.getLong(key, defaultInt);
    }

    public void saveParam(String key, long value){
        shareEditor.putLong(key, value).commit();
    }

    public void removeKey(String key){
        shareEditor.remove(key).commit();
    }
}
