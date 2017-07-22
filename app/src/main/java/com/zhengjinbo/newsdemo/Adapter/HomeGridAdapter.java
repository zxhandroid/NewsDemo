package com.zhengjinbo.newsdemo.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.BasicAdapter;
import com.zhengjinbo.newsdemo.base.BasicViewHolder;
import com.zhengjinbo.newsdemo.bean.NewsClassifyBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhengjinbo.
 */

public class HomeGridAdapter
        extends BasicAdapter<NewsClassifyBean.TngouBean>
{


    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_keywords)
    TextView mTvKeywords;
    @BindView(R.id.tv_des)
    TextView mTvDes;
    public HomeGridAdapter(List<NewsClassifyBean.TngouBean> datas) {
        super(datas);
    }

    public HomeGridAdapter() {

    }

    @Override
    protected void initItemUi(BasicViewHolder viewHolder, int position, Context context) {
        NewsClassifyBean.TngouBean item = getItem(position);
        mTvName.setText(item.getName());
        mTvKeywords.setText(item.getKeywords());
        mTvDes.setText(item.getDescription());
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_home;
    }
}
