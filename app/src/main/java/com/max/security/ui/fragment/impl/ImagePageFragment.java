package com.max.security.ui.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.max.security.R;
import com.max.security.mvp.presenters.impl.ImagePagePresenter;
import com.max.security.mvp.views.impl.ImagePageView;
import com.max.security.view.BetterFab;
import com.max.security.ui.fragment.BaseFragment;
import com.pnikosis.materialishprogress.ProgressWheel;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ImagePageFragment extends BaseFragment implements ImagePageView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.progress_wheel)
    ProgressWheel progressWheel;
    @Bind(R.id.fab)
    BetterFab fab;
    @Inject
    ImagePagePresenter imagePresenter;

    public static ImagePageFragment newInstance(String param1, String param2) {
        ImagePageFragment fragment = new ImagePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imagePresenter.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imagePresenter.onViewCreated();
    }

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_image;
    }

    @Override
    protected void initialPresenter() {
        imagePresenter.attachView(this);
    }
}
