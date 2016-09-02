package com.max.security.injector.component;

import android.app.Activity;
import android.content.Context;

import com.max.security.injector.ContextLifeCycle;
import com.max.security.ui.fragment.impl.ImagePageFragment;
import com.max.security.injector.Fragment;
import com.max.security.injector.module.FragmentModule;

import dagger.Component;

/**
 * Created by lgp on 2015/9/3.
 */
@Fragment
@Component(dependencies = {ActivityComponent.class}, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(ImagePageFragment fragment);
}
