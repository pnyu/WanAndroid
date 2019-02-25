package com.bamboo.pengyu.wanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bamboo.pengyu.wanandroid.R;
import com.bamboo.pengyu.wanandroid.net.entity.Article;

import java.util.List;

/**
 * Created by pengyu on 2019/2/23.
 */

public class HomeAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Article> datas;

    public HomeAdapter(Context context, List<Article> datas) {
        inflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.home_item, parent, false);
            holder = new ViewHolder();

            holder.tvTagsName = convertView.findViewById(R.id.tv_home_tags_name);
            holder.tvAuthor = convertView.findViewById(R.id.tv_home_author);
            holder.tvNiceDate = convertView.findViewById(R.id.tv_home_nicedate);
            holder.tvTitle = convertView.findViewById(R.id.tv_home_title);
            holder.tvSuperChapterName = convertView.findViewById(R.id.tv_home_superChapterName);
            holder.tvChapterName = convertView.findViewById(R.id.tv_home_chapterName);
            holder.ivMark = convertView.findViewById(R.id.iv_home_mark);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvTagsName.setText(datas.get(position).getTags().get(0).getName());
        holder.tvAuthor.setText(datas.get(position).getAuthor());
        holder.tvNiceDate.setText(datas.get(position).getNiceDate());
        holder.tvTitle.setText(datas.get(position).getTitle());
        holder.tvSuperChapterName.setText(datas.get(position).getSuperChapterName());
        holder.tvChapterName.setText(datas.get(position).getChapterName());
        return convertView;
    }

    private class ViewHolder{
        TextView tvTagsName;
        TextView tvAuthor;
        TextView tvNiceDate;
        TextView tvTitle;
        TextView tvSuperChapterName;
        TextView tvChapterName;
        ImageView ivMark;
    }
}
