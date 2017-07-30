package com.zhengjinbo.newsdemo.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.AppConstants;
import com.zhengjinbo.newsdemo.base.BaseActivity;
import com.zhengjinbo.newsdemo.base.FragmentCommon;
import com.zhengjinbo.newsdemo.fragment.TweetFragment;
import com.zhengjinbo.newsdemo.fragment.MeFragment;
import com.zhengjinbo.newsdemo.fragment.NewsFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
/**
 * Created by zhengjinbo.
 * 主界面
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.bottom_navigation_bar)
    public BottomNavigationBar mBottomNavigationBar;
    private ArrayList<Fragment> mFragmentList;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initBottomNaviBar();
        initFragmentList();
    }


    @Override
    protected void initListener() {
        mBottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 初始化底部选项卡
     */
    private void initBottomNaviBar() {
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_nav_news_actived,
                                                              AppConstants.TAB_NEWS).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_nav_tweet_actived,
                        AppConstants.TAB_TWEET).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_nav_my_pressed,
                        AppConstants.TAB_ME).setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    /**
     * 初始化fragment
     */
    private void initFragmentList() {
        mFragmentList = new ArrayList<Fragment>();
        NewsFragment  newsFragment = (NewsFragment) FragmentCommon.newInstance(AppConstants.TAB_NEWS);
        TweetFragment tweetFragment = (TweetFragment) FragmentCommon.newInstance(AppConstants.TAB_TWEET);
        //newsFragment实例化就注册事件，防止点击首页分类时，事件还未注册没法接收到
        EventBus.getDefault().register(newsFragment);
        MeFragment meFragment = (MeFragment) FragmentCommon.newInstance(AppConstants.TAB_ME);

        mFragmentList.add(newsFragment);
        mFragmentList.add(tweetFragment);
        mFragmentList.add(meFragment);
        setDefaultFragment();
    }

    /**
     * 设置默认的fragment
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_content, mFragmentList.get(0)).commitAllowingStateLoss();
    }


    @Override
    public void onTabSelected(int position) {
        if (mFragmentList != null) {
            if (position < mFragmentList.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Fragment fragment = mFragmentList.get(position);
                if (fragment.isAdded()) {
                    transaction.show(fragment);
                } else {
                    transaction.add(R.id.fl_content, fragment);
                }
                transaction.commitAllowingStateLoss();
            }
            hideUnselected(position);
        }
    }

    /**
     * 当调用mBottomNavigationBar.selectTab(int index)时不会走onTabUnselected()方法，
     * 所以要在onTabSelected()中对未选中的进行隐藏
     * @param position  选中的索引
     */
    private void hideUnselected(int position) {
        for (int i = 0; i < mFragmentList.size(); i++) {
            if (i != position){
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Fragment fragment = mFragmentList.get(i);
                if (fragment.isAdded() && !fragment.isHidden()){
                    transaction.hide(fragment);
                    transaction.commitAllowingStateLoss();
                }
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {
        if (mFragmentList != null) {
            if (position < mFragmentList.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Fragment fragment = mFragmentList.get(position);
                if (!fragment.isHidden()) {
                    transaction.hide(fragment);
                    transaction.commitAllowingStateLoss();
                }
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }
}
