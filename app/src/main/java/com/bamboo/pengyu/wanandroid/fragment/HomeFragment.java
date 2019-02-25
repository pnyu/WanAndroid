package com.bamboo.pengyu.wanandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bamboo.pengyu.wanandroid.R;
import com.bamboo.pengyu.wanandroid.adapter.HomeAdapter;
import com.bamboo.pengyu.wanandroid.net.NetUtil;
import com.bamboo.pengyu.wanandroid.net.api.HomeApi;
import com.bamboo.pengyu.wanandroid.net.entity.Article;
import com.bamboo.pengyu.wanandroid.net.entity.Articles;
import com.bamboo.pengyu.wanandroid.net.entity.BaseResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by pengyu on 2019/1/16.
 */

public class HomeFragment extends Fragment {
    private List<Article> datas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        ListView lv = view.findViewById(R.id.lv_home);

        HomeApi homeApi = NetUtil.getHomeApi();
        final Call<BaseResponse<Articles>> result = homeApi.getArticle(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<BaseResponse<Articles>> response = result.execute();
                    datas = response.body().getData().getDatas();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        if(datas != null) {
            lv.setAdapter(new HomeAdapter(this.getContext(),datas));
        }


        return view;
    }
}
