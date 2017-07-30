package com.zhengjinbo.newsdemo.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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

public abstract class BaseFragment
        extends Fragment
{

    private Unbinder       mBind;
    private ProgressDialog mProgressDialog;
    protected Context mContext;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(getLayout(), container, false);
        mBind = ButterKnife.bind(this, view);
        initDialog();
        initData();
        initListener();
        return view;
    }

    /**
     * 初始化数据加载dialog
     */
    private void initDialog() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("数据加载中...");
        mProgressDialog.setCanceledOnTouchOutside(false);
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

    /**
     * 显示数据加载对话框
     */
    protected void showDialog(){
        if (mProgressDialog!= null && !mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    /**
     * 隐藏数据加载对话框
     */
    protected void hideDialog(){
        if (mProgressDialog!= null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定
        mBind.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mProgressDialog !=null) {
            mProgressDialog = null;
        }
    }
}
