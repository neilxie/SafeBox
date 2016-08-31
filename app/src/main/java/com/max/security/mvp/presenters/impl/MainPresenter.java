package com.max.security.mvp.presenters.impl;

import android.content.Context;
import android.os.Bundle;

import com.max.security.R;
import com.max.security.injector.ContextLifeCycle;
import com.max.security.model.FileModel;
import com.max.security.mvp.presenters.Presenter;
import com.max.security.mvp.views.View;
import com.max.security.mvp.views.impl.MainView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Max on 2016/8/11.
 */
public class MainPresenter implements Presenter {

    private MainView mainView;
    private Context mContext;
    private List<String> drawerList;
    private FileModel.FileType currentPageType = FileModel.FileType.IMAGE;

    @Inject
    public MainPresenter(@ContextLifeCycle("Activity")Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mainView.initToolbar();
        initDrawer();
        initLayoutManager();
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
        mainView = (MainView) v;
    }

    public void onDrawerItemSelect(int position) {
        currentPageType = FileModel.FileType.mapValueToType(position);
        mainView.closeDrawer();
    }

    public void onDrawerOpened(){
        mainView.setToolbarTitle(mContext.getString(R.string.app_name));
    }

    public void onDrawerClosed(){
        mainView.setToolbarTitle(drawerList.get(currentPageType.getValue()));
    }

    private void initDrawer() {
        drawerList = Arrays.asList(mContext.getResources()
                .getStringArray(R.array.drawer_content));
        mainView.initDrawerView(drawerList);
        mainView.setDrawerItemChecked(currentPageType.getValue());
        mainView.setToolbarTitle(drawerList.get(currentPageType.getValue()));
    }

    public void OnNavigationOnClick(){
        mainView.openOrCloseDrawer();
    }

    private void initLayoutManager() {

    }
}
