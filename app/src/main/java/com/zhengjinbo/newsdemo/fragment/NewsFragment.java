package com.zhengjinbo.newsdemo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.AppConstants;
import com.zhengjinbo.newsdemo.base.BaseFragment;
import com.zhengjinbo.newsdemo.event.HomeItemClickEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    //是否第一次加载数据,默认true
    private boolean isFirstRequest =true;

    @Override
    protected int getLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String news = arguments.getString(AppConstants.KEY_NEWS);
            mTvTitle.setText(news);
        }
    }

    @Override
    protected void initListener() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN,priority = 100)    //在ui线程执行,优先级最高
    public void onHomeItemClickEvent(HomeItemClickEvent event){
        Log.e("zxh","点击了" + event.getId());

    }


    //fragment 使用hide 和show
    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.e("zxh","hidden==" +hidden);

    }



    /**
     * 请求网络数据
     * @param newsId
     */
    private void requestData(int newsId) {

    }

    //---------------------------------------------
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e("zxh","onAttach==");
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("zxh","onCreate==");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        Log.e("zxh","onCreateView==");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("zxh","onActivityCreated==");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("zxh","onStart==");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("zxh","onResume==");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("zxh","onPause==");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("zxh","onStop==");
    }

    //在fragment 销毁时取消注册
    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        Log.e("zxh","onDestroyView==");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("zxh","onDestroy==");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("zxh","onDetach==");
    }
}
