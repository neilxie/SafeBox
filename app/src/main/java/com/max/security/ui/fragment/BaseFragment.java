package com.max.security.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.security.injector.component.DaggerFragmentComponent;
import com.max.security.injector.component.FragmentComponent;
import com.max.security.injector.module.FragmentModule;
import com.max.security.ui.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/1.
 */
public abstract class BaseFragment extends Fragment {

    public static final String ARG_PARAM1 = "params1";
    public static final String ARG_PARAM2 = "params2";

    String mParam1;
    String mParam2;

    protected FragmentComponent fragmentComponent;
    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null) {
            mParam1 = args.getString(ARG_PARAM1);
            mParam2 = args.getString(ARG_PARAM2);
        }

        fragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(mActivity.getActivityComponent())
                .fragmentModule(new FragmentModule(this))
                .build();


        initInject();
        initialPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutView(), container, false);
        ButterKnife.bind(view);
        return view;
    }

    protected abstract @LayoutRes int getLayoutView();
    protected abstract void initialPresenter();
    protected abstract void initInject();

}
