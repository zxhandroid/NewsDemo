package com.zhengjinbo.newsdemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhengjinbo.newsdemo.Adapter.HomeGridAdapter;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.BaseFragment;
import com.zhengjinbo.newsdemo.bean.NewsClassifyBean;
import com.zhengjinbo.newsdemo.bean.NewsPaperBean;
import com.zhengjinbo.newsdemo.http.NewsService;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhengjinbo.
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsService newsService = retrofit.create(NewsService.class);
        Call<NewsPaperBean> newsClassify = newsService.getNewsClassifyTest();
        newsClassify.enqueue(new Callback<NewsPaperBean>() {
            @Override
            public void onResponse(Call<NewsPaperBean> call, Response<NewsPaperBean> response) {
                NewsPaperBean body = response.body();
                Toast.makeText(getActivity(), new Gson().toJson(body), Toast.LENGTH_SHORT).show();
                Log.e("zxh", "body==" + new Gson().toJson(body));
            }

            @Override
            public void onFailure(Call<NewsPaperBean> call, Throwable t) {

            }
        });

        //获取新闻分类
        Retrofit rtf = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsService service = rtf.create(NewsService.class);
        Call<NewsClassifyBean> classify = service.getNewsClassify();
        classify.enqueue(new Callback<NewsClassifyBean>() {
            @Override
            public void onResponse(Call<NewsClassifyBean> call, Response<NewsClassifyBean> response) {
                NewsClassifyBean body = response.body();

                Log.e("zxh", "body==" + new Gson().toJson(body));
            }

            @Override
            public void onFailure(Call<NewsClassifyBean> call, Throwable t) {

            }
        });


    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString("home");
            mTvTitle.setText(title);
        }
    }

    @Override
    protected void initListener() {

    }

}
