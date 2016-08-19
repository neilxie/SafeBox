package com.max.security.mvp.presenters;

import android.os.Bundle;

import com.max.security.mvp.views.View;

/**
 * Created by Max on 2016/8/11.
 */
public interface Presenter {

    void onCreate(Bundle savedInstanceState);

    void onResume();

    void onStart();

    void onPause();

    void onStop();

    void onDestroy();

    void attachView(View v);
}
