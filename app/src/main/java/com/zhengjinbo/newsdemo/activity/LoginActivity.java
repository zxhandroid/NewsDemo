package com.zhengjinbo.newsdemo.activity;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.BaseActivity;
import butterknife.BindView;

/**
 * Created by zhengjinbo.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;

    }

    @Override
    protected void initData() {
        mTvTitle.setText("登陆");

    }

    @Override
    protected void initListener() {

    }
}
