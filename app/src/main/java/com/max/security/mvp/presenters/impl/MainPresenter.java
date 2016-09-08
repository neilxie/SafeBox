package com.max.security.mvp.presenters.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.max.security.R;
import com.max.security.injector.ContextLifeCycle;
import com.max.security.model.FileModelType;
import com.max.security.mvp.presenters.Presenter;
import com.max.security.mvp.views.View;
import com.max.security.mvp.views.impl.MainView;
import com.max.security.ui.fragment.impl.ImagePageFragment;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import static com.max.security.model.FileModelType.IMAGE;

/**
 * Created by Max on 2016/8/11.
 */
public class MainPresenter implements Presenter {

    private MainView mainView;
    private Context mContext;
    private List<String> drawerList;
    private FileModelType currentPageType;
    private Fragment imagePageFragment;
    private Fragment videoPageFragment;
    private Fragment audioPageFragment;
    private Fragment filePageFragment;
    private Fragment currentPageFragment;

    @Inject
    public MainPresenter(@ContextLifeCycle("Activity")Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mainView.initToolbar();
        initPage();
        initDrawer();
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
        currentPageType = FileModelType.mapValueToType(position);
        mainView.closeDrawer();
        switchPage();
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

    private void initPage() {
        currentPageType = IMAGE;
        switchPage();
    }

    private void switchPage() {
        if(currentPageFragment != null) {
            mainView.hideFragment(currentPageFragment);
        }

        currentPageFragment = getCurrentPage();
        mainView.showFragment(currentPageFragment);
    }

    private Fragment getCurrentPage() {
        Fragment fragment = null;
        switch (currentPageType) {
            case IMAGE:
                if(imagePageFragment == null) {
                    imagePageFragment = ImagePageFragment.newInstance("", "");
                }
                fragment = imagePageFragment;
                break;
            case VIDEO:
                fragment = videoPageFragment;
                break;
            case AUDIO:
                fragment = audioPageFragment;
                break;
            case FILE:
                fragment = filePageFragment;
                break;
        }

        return fragment;
    }

}
