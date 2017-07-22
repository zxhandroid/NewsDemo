package com.zhengjinbo.newsdemo.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zhengjinbo.
 */

public abstract class BasicAdapter<T> extends BaseAdapter {

    private List<T> mDatas = new ArrayList<T>();
    //无参构造
    public BasicAdapter(){}

    public BasicAdapter(List<T> datas) {
        mDatas =datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BasicViewHolder viewHolder = BasicViewHolder.newInstance(convertView,parent.getContext(),getItemLayout());
        convertView = viewHolder.getConvertView();
        ButterKnife.bind(this, convertView);
        //让子adapter去设置相应的Ui
        initItemUi(viewHolder,position,parent.getContext());

        return convertView;
    }

    /**
     * 清空并添加全部数据到adapter中
     * @param datas
     */
    public void setDatas(List<T> datas){
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 追加数据
     * @param datas
     */
    public void setMoreDatas(List<T> datas){
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 获取adapter中的数据集
     * @return
     */
    public List<T> getDatas(){
        return mDatas;
    }

    /**
     * 初始化子类的Ui
     * @param viewHolder
     * @param position
     * @param context
     */
    protected abstract void initItemUi(BasicViewHolder viewHolder, int position, Context context);

    /**
     * 子类item布局
     * @return
     */
    protected abstract int getItemLayout();

}
