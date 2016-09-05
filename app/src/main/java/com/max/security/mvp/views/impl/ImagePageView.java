package com.max.security.mvp.views.impl;

import android.support.v7.widget.RecyclerView;

import com.max.security.greendao.FileModel;
import com.max.security.mvp.views.View;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public interface ImagePageView extends View {

    void setLayoutManager(RecyclerView.LayoutManager layoutManager);

    void showProgressWheel(boolean isShow);

    void initRecyclerView(List<FileModel> files);

    void showEmpty(boolean isShow);
}
