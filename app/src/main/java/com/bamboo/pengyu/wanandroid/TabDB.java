package com.bamboo.pengyu.wanandroid;

import com.bamboo.pengyu.wanandroid.fragment.HomeFragment;
import com.bamboo.pengyu.wanandroid.fragment.KnowledgeFragment;
import com.bamboo.pengyu.wanandroid.fragment.NavigationFragment;
import com.bamboo.pengyu.wanandroid.fragment.ProjectFragment;
import com.bamboo.pengyu.wanandroid.fragment.WechatFragment;

/**
 * Created by pengyu on 2019/1/16.
 */

public class TabDB {
    public static String[] getTabsTxt() {
        String[] tabs = {"首页", "知识体系", "公众号", "导航", "项目"};
        return tabs;
    }

    public static int[] getTabsImg() {
        int[] ids = {R.mipmap.icon_home_normal, R.mipmap.icon_knowledge_normal, R.mipmap.icon_wechat_normal,
        R.mipmap.icon_navigation_normal, R.mipmap.icon_project_normal};
        return ids;
    }

    public static int[] getTabsImgLight() {
        int[] ids = {R.mipmap.icon_home_light, R.mipmap.icon_knowledge_light, R.mipmap.icon_wechat_light,
        R.mipmap.icon_navigation_light, R.mipmap.icon_project_light};
        return ids;
    }

    public static Class[] getFragments() {
        Class[] classes = {HomeFragment.class, KnowledgeFragment.class, WechatFragment.class, NavigationFragment.class, ProjectFragment.class};
        return classes;
    }
}
