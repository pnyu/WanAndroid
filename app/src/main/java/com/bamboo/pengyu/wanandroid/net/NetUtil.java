package com.bamboo.pengyu.wanandroid.net;

import com.bamboo.pengyu.wanandroid.net.api.HomeApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pengyu on 2019/2/24.
 */

public class NetUtil {

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static HomeApi getHomeApi() {
        return getRetrofit().create(HomeApi.class);
    }
}
