package com.zhengjinbo.newsdemo.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhengjinbo.
 */
public class HttpUtils {

    public static <B> B requestNetData(String baseUrl,Class<B> clazz){
        Retrofit rtf = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return rtf.create(clazz);
    }
}
