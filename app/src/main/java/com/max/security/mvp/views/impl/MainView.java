package com.max.security.mvp.views.impl;

import android.support.v4.app.Fragment;

import com.max.security.mvp.views.View;

import java.util.List;

/**
 * Created by Max on 2016/8/11.
 */
public interface MainView extends View {

    void initToolbar();
    void initDrawerView(List<String> list);
    void setDrawerItemChecked(int position);
    void setToolbarTitle(String title);
    void closeDrawer();
    void openOrCloseDrawer();
    void showFragment(Fragment fragment);
    void hideFragment(Fragment fragment);
}
