package com.zhengjinbo.newsdemo.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.BaseActivity;
import com.zhengjinbo.newsdemo.base.FragmentCommon;

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
    private FragmentTransaction mTransaction;

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
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home_on,
                "首页").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.news_on,
                        "新闻").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.grade_on,
                        "我的").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    /**
     * 初始化fragment
     */
    private void initFragmentList() {
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(FragmentCommon.newInstance("首页"));
        mFragmentList.add(FragmentCommon.newInstance("新闻"));
        mFragmentList.add(FragmentCommon.newInstance("我的"));
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
//        Toast.makeText(this, "position:" + position, Toast.LENGTH_SHORT)
//                .show();
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
                //            invalidateToolbarExists();
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
//        Toast.makeText(this, "onTabUnselected=position:" + position, Toast.LENGTH_SHORT)
//             .show();
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
