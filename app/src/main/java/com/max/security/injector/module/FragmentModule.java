package com.max.security.injector.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.max.security.injector.ContextLifeCycle;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lgp on 2015/9/3.
 */
@Module
public class FragmentModule {

    Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @com.max.security.injector.Fragment
    @ContextLifeCycle("Activity")
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @com.max.security.injector.Fragment
    public Fragment provideFragment() {
        return mFragment;
    }
}
