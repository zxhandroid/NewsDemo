package com.zhengjinbo.newsdemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by zhengjinbo.
 */
public class NewsFragment
        extends BaseFragment
{
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar  mToolbar;

    @Override
    protected int getLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String news = arguments.getString("news");
            mTvTitle.setText(news);
        }
    }

    @Override
    protected void initListener() {

    }

}
