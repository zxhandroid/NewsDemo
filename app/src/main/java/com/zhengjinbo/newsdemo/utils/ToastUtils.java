package com.zhengjinbo.newsdemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zengxianghui900 on 17/7/22.
 */

public class ToastUtils {
    /**
     * 显示短提示
     * @param context
     * @param msg
     */
    public static void showShortToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示长提示
     * @param context
     * @param msg
     */
    public static void showLongToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }


}
