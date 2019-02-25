package com.bamboo.pengyu.wanandroid.net.api;

import com.bamboo.pengyu.wanandroid.net.entity.Article;
import com.bamboo.pengyu.wanandroid.net.entity.Articles;
import com.bamboo.pengyu.wanandroid.net.entity.Banner;
import com.bamboo.pengyu.wanandroid.net.entity.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by pengyu on 2019/2/24.
 */

public interface HomeApi {
    @GET("banner/json")
    Call<Banner> getBanner();

    @GET("article/list/{pageNum}/json")
    Call<BaseResponse<Articles>> getArticle(@Path("pageNum") int pageNum);
}
