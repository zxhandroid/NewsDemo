package com.zhengjinbo.newsdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhengjinbo.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
        mBind = ButterKnife.bind(this);
        initData();
        initListener();

    }

    /**
     * 抽象方法，获取布局
     * @return
     */
    protected abstract int getLayout();


    /**
     * 抽象方法，初始化数据
     */
    protected abstract void initData();

    /**
     * 抽象方法，初始化监听事件
     */
    protected abstract void initListener();





    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
