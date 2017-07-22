package com.zhengjinbo.newsdemo.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by zhengjinbo.
 */

public class BasicViewHolder {

    private View mConvertView;
    private SparseArray<View> mViews;

    private BasicViewHolder(Context context, int layoutId){
        mConvertView = LayoutInflater.from(context).inflate(layoutId, null);
        //为mConvertView设置tag
        mConvertView.setTag(this);
        //创建一个集合来存储Id
        mViews = new SparseArray<View>();

    }

    public static BasicViewHolder newInstance(View convertView, Context context, int layoutId){
        if (convertView == null){
            return new BasicViewHolder(context, layoutId);
        }else{
            return (BasicViewHolder) convertView.getTag();
        }
    }

    /**
     *  暴露一个方法提供mConvertView
     */
    public View getConvertView(){
        return mConvertView;
    }

    /**
     * 暴露一个方法获取id对应的View
     */
    public <T extends View> T getItemView(int id){
        View view = mViews.get(id);
        if (view == null){
            view = mConvertView.findViewById(id);
            mViews.append(id,view);
        }
        return (T) view;
    }


}
