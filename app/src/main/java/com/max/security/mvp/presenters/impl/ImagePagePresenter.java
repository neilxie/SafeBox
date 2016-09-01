package com.max.security.mvp.presenters.impl;

import android.content.Context;
import android.os.Bundle;

import com.max.security.injector.ContextLifeCycle;
import com.max.security.mvp.presenters.FragmentPresenter;
import com.max.security.mvp.views.View;
import com.max.security.mvp.views.impl.ImagePageView;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ImagePagePresenter implements FragmentPresenter {

    private ImagePageView imageFgView;
    private Context mContext;

    @Inject
    public ImagePagePresenter(@ContextLifeCycle("Activity") Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void attachView(View v) {
        imageFgView = (ImagePageView) v;
    }

    @Override
    public void onViewCreated() {

    }
}
