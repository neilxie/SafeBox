package com.max.security.mvp.presenters.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.max.security.injector.ContextLifeCycle;
import com.max.security.model.FileModel;
import com.max.security.mvp.presenters.FragmentPresenter;
import com.max.security.mvp.views.View;
import com.max.security.mvp.views.impl.ImagePageView;
import com.max.security.utils.ObservableUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ImagePagePresenter implements FragmentPresenter {

    private ImagePageView imagePageView;
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
        imagePageView = (ImagePageView) v;
    }

    @Override
    public void onViewCreated() {
        initLayoutManager();
        loadData();
    }

    private void initLayoutManager() {
        imagePageView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
    }

    private void loadData() {
        ObservableUtils.getFileModelByType(FileModel.FileType.IMAGE.getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<FileModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<FileModel> fileModels) {
                        initRecyclerView(fileModels);
                    }
                });
    }

    private void initRecyclerView(List<FileModel> fileModels) {
        imagePageView.showProgressWheel(false);
        imagePageView.initRecyclerView(fileModels);
    }
}
