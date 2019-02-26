package com.bamboo.pengyu.wanandroid.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bamboo.pengyu.wanandroid.R;
import com.bamboo.pengyu.wanandroid.adapter.HomeAdapter;
import com.bamboo.pengyu.wanandroid.net.NetUtil;
import com.bamboo.pengyu.wanandroid.net.api.HomeApi;
import com.bamboo.pengyu.wanandroid.net.entity.Article;
import com.bamboo.pengyu.wanandroid.net.entity.Articles;
import com.bamboo.pengyu.wanandroid.net.entity.BaseResponse;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by pengyu on 2019/1/16.
 */

public class HomeFragment extends Fragment {
    private List<Article> articleDatas;
    private List<com.bamboo.pengyu.wanandroid.net.entity.Banner> bannerDatas;
    private Banner banner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        ListView lv = view.findViewById(R.id.lv_home);

        HomeApi homeApi = NetUtil.getHomeApi();
        final Call<BaseResponse<Articles>> articleResult = homeApi.getArticle(0);
        final Call<BaseResponse<List<com.bamboo.pengyu.wanandroid.net.entity.Banner>>> bannerResult = homeApi.getBanner();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<BaseResponse<Articles>> articleResponse = articleResult.execute();
                    articleDatas = articleResponse.body().getData().getDatas();
                    Response<BaseResponse<List<com.bamboo.pengyu.wanandroid.net.entity.Banner>>> bannerResponse = bannerResult.execute();
                    bannerDatas = bannerResponse.body().getData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        if(articleDatas != null && bannerDatas != null) {
            List<Uri> uris = new ArrayList<>();
            for(int i=0;i<bannerDatas.size();i++) {
                uris.add(new Uri.Builder().path(bannerDatas.get(i).getImagePath()).build());
            }
            addBannerToLv(inflater,lv,uris);
            lv.setAdapter(new HomeAdapter(this.getContext(),articleDatas));
        }



        return view;
    }

    private void addBannerToLv(LayoutInflater inflater,ListView lv, List<Uri> uris) {
        banner = inflater.inflate(R.layout.home_banner,null).findViewById(R.id.banner);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setImages(uris);
        lv.addHeaderView(banner);
    }
}
