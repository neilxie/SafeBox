package com.max.security.ui;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.max.security.App;
import com.max.security.R;
import com.max.security.adapter.DrawerListAdapter;
import com.max.security.adapter.SimpleListAdapter;
import com.max.security.injector.component.DaggerActivityComponent;
import com.max.security.injector.module.ActivityModule;
import com.max.security.mvp.presenters.impl.MainPresenter;
import com.max.security.mvp.views.impl.MainView;
import com.max.security.utils.ToolbarUtils;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.refresher)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.left_drawer_listview)
    ListView mDrawerMenuListView;
    @Bind(R.id.left_drawer)
    View drawerRootView;
    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.progress_wheel)
    ProgressWheel progressWheel;
    @Inject MainPresenter mainPresenter;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        launchWithNoAnim();
        super.onCreate(savedInstanceState);
        mainPresenter.attachView(this);
        mainPresenter.onCreate(savedInstanceState);
    }

    @Override
    protected void initializeDependencyInjector() {
        App app = (App) getApplication();
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(app.getAppComponent())
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initToolbar() {
        ToolbarUtils.initToolbar(toolbar, this);
    }

    @Override
    public void initDrawerView(List<String> list) {
        SimpleListAdapter adapter = new DrawerListAdapter(this, list);
        mDrawerMenuListView.setAdapter(adapter);
        mDrawerMenuListView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) ->
                mainPresenter.onDrawerItemSelect(position));
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                mainPresenter.onDrawerOpened();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                mainPresenter.onDrawerClosed();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setScrimColor(getCompactColor(R.color.drawer_scrim_color));
    }

    @Override
    public void setDrawerItemChecked(int position) {
        mDrawerMenuListView.setItemChecked(position, true);
    }

    @Override
    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(drawerRootView)) {
            mDrawerLayout.closeDrawer(drawerRootView);
        }
    }


}
