package com.zhengjinbo.newsdemo.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.zhengjinbo.newsdemo.fragment.TweetFragment;
import com.zhengjinbo.newsdemo.fragment.MeFragment;
import com.zhengjinbo.newsdemo.fragment.NewsFragment;


/**
 * Created by zhengjinbo.
 */
public class FragmentCommon {

    private static Fragment fragment;

    public static Fragment newInstance(String text) {
        switch (text) {
            case AppConstants.TAB_NEWS:
            fragment = new NewsFragment();
            Bundle newsData = new Bundle();
            newsData.putString(AppConstants.KEY_NEWS,text);
            fragment.setArguments(newsData);
            break;
            case AppConstants.TAB_TWEET:
                fragment = new TweetFragment();
                Bundle homeData = new Bundle();
                homeData.putString(AppConstants.KEY_TWEET,text);
                fragment.setArguments(homeData);
                break;
            case AppConstants.TAB_ME:
                fragment = new MeFragment();
                Bundle meData = new Bundle();
                meData.putString(AppConstants.KEY_ME,text);
                fragment.setArguments(meData);
                break;
            default:
                 break;
        }

        return fragment;
    }
}
