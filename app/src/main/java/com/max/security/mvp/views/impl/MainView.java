package com.max.security.mvp.views.impl;

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
}
