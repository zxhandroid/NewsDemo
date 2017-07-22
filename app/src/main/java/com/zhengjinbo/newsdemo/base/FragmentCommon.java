package com.zhengjinbo.newsdemo.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.zhengjinbo.newsdemo.fragment.HomeFragment;
import com.zhengjinbo.newsdemo.fragment.MeFragment;
import com.zhengjinbo.newsdemo.fragment.NewsFragment;


/**
 * Created by zhengjinbo.
 */
public class FragmentCommon {

    private static Fragment fragment;

    public static Fragment newInstance(String text) {
        switch (text) {
            case "首页":
                fragment = new HomeFragment();
                Bundle homeData = new Bundle();
                homeData.putString("home",text);
                fragment.setArguments(homeData);
                break;
            case "新闻":
                fragment = new NewsFragment();
                Bundle newsData = new Bundle();
                newsData.putString("news",text);
                fragment.setArguments(newsData);

                break;
            case "我的":
                fragment = new MeFragment();
                Bundle meData = new Bundle();
                meData.putString("me",text);
                fragment.setArguments(meData);
                break;
            default:
                 break;
        }

        return fragment;
    }
}
