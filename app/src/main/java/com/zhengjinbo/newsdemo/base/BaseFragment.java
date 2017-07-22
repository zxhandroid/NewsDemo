package com.zhengjinbo.newsdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhengjinbo.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(getLayout(), container, false);
        mBind = ButterKnife.bind(this, view);
        initData();
        initListener();
        initRetrofit();
        return view;
    }

    private void initRetrofit() {

    }

    /**
     * 抽象方法，初始化布局
     * @return
     */
    protected abstract int getLayout();

    /**
     * 抽象方法，初始化fragment数据
     */
    protected abstract void initData();

    /**
     * 抽象方法，初始化事件
     */
    protected abstract void initListener();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定
        mBind.unbind();
    }
}
