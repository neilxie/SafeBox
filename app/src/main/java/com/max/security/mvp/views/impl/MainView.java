package com.max.security.mvp.views.impl;

import android.support.v7.widget.RecyclerView;

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
    void setLayoutManager(RecyclerView.LayoutManager manager);
}
