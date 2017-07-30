package com.zhengjinbo.newsdemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhengjinbo.newsdemo.Adapter.HomeGridAdapter;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.activity.MainActivity;
import com.zhengjinbo.newsdemo.base.AppConstants;
import com.zhengjinbo.newsdemo.base.BaseFragment;
import com.zhengjinbo.newsdemo.bean.NewsClassifyBean;
import com.zhengjinbo.newsdemo.event.HomeItemClickEvent;
import com.zhengjinbo.newsdemo.http.HttpUtils;
import com.zhengjinbo.newsdemo.http.NewsService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengjinbo.
 */
public class TweetFragment
        extends BaseFragment
        implements AdapterView.OnItemClickListener
{
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar  mToolbar;
    @BindView(R.id.grid)
    GridView mGrid;
    private HomeGridAdapter mGridAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        initTitle();
        initAdatper();
        initRequestData();
    }

    private void initAdatper() {
        mGridAdapter = new HomeGridAdapter();
        mGrid.setAdapter(mGridAdapter);
    }

    private void initRequestData() {
        //以下为测试
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://news-at.zhihu.com/api/4/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        NewsService         newsService  = retrofit.create(NewsService.class);
//        Call<NewsPaperBean> newsClassify = newsService.getNewsClassifyTest();
//        newsClassify.enqueue(new Callback<NewsPaperBean>() {
//            @Override
//            public void onResponse(Call<NewsPaperBean> call, Response<NewsPaperBean> response) {
//                NewsPaperBean body = response.body();
//                Toast.makeText(getActivity(),new Gson().toJson(body),Toast.LENGTH_SHORT).show();
//                Log.e("zxh", "body111==" + new Gson().toJson(body));
//            }
//
//            @Override
//            public void onFailure(Call<NewsPaperBean> call, Throwable t) {
//
//            }
//        });
        //显示加载对话框
        showDialog();

        //获取新闻分类
        NewsService                      service  = HttpUtils.requestNetData(NewsService.BASE_URL_TEST, NewsService.class);
        Call<NewsClassifyBean.TngouBean> classify = service.getNewsClassify();
        classify.enqueue(new Callback<NewsClassifyBean.TngouBean>() {
            @Override
            public void onResponse(Call<NewsClassifyBean.TngouBean> call, Response<NewsClassifyBean.TngouBean> response) {
                NewsClassifyBean.TngouBean body = response.body();
                Log.e("zxh", "body==" + new Gson().toJson(body));
                //造假数据
                initFakeData();
                //隐藏加载对话框
                hideDialog();
            }

            @Override
            public void onFailure(Call<NewsClassifyBean.TngouBean> call, Throwable t) {

            }
        });

    }

    /**
     * 造假数据
     */
    private void initFakeData() {
        List<NewsClassifyBean.TngouBean> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            NewsClassifyBean.TngouBean tngouBean = new NewsClassifyBean.TngouBean();
            tngouBean.setId(i+1);
            tngouBean.setName("民生热点"+ i);
            tngouBean.setKeywords(i+ "民生热点事件 民生热词排行 天狗实时事件");
            tngouBean.setDescription(i + "天狗实时事件:民生热点事件，民生热词排行 做最好的民生热点、热词提取；推送最新的民生实时事件，挖掘最新的民生热词。");
            list.add(tngouBean);
        }
        mGridAdapter.setDatas(list);
    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString(AppConstants.KEY_TWEET);
            mTvTitle.setText(title);
        }
    }

    @Override
    protected void initListener() {
        mGrid.setOnItemClickListener(this);
    }
    /**条目点击事件*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        List<NewsClassifyBean.TngouBean> datas = mGridAdapter.getDatas();
        NewsClassifyBean.TngouBean       tngouBean = datas.get(position);
        //点击跳转到新闻tab
        ((MainActivity)mContext).mBottomNavigationBar.selectTab(1);
        //传递一个事件，将新闻id传递过去
        EventBus.getDefault().post(new HomeItemClickEvent(tngouBean.getId()));
    }
}
