package com.bamboo.pengyu.wanandroid;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener {

    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.frame_content);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(this);
        initTab();
    }

    private void initTab() {
        String[] tabs = TabDB.getTabsTxt();
        for(int i=0; i< tabs.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec,TabDB.getFragments()[i],null);
            tabHost.setTag(i);
        }
    }

    private View getTabView(int idx) {
        View view = LayoutInflater.from(this).inflate(R.layout.footer_tabs,null);
        ((TextView) view.findViewById(R.id.tv_tab_name)).setText(TabDB.getTabsTxt()[idx]);
        if(idx == 0) {
            ((TextView) view.findViewById(R.id.tv_tab_name)).setTextColor(Color.RED);
            ((ImageView) view.findViewById(R.id.iv_tab_icon)).setImageResource(TabDB.getTabsImgLight()[idx]);
        } else {
            ((ImageView) view.findViewById(R.id.iv_tab_icon)).setImageResource(TabDB.getTabsImg()[idx]);
        }
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        TabWidget tabWidget = tabHost.getTabWidget();
        for(int i=0; i<tabWidget.getChildCount(); i++) {
            View view = tabWidget.getChildTabViewAt(i);
            ImageView imageView = view.findViewById(R.id.iv_tab_icon);
            if(i == tabHost.getCurrentTab()) {
                ((TextView) view.findViewById(R.id.tv_tab_name)).setTextColor(Color.RED);
                imageView.setImageResource(TabDB.getTabsImgLight()[i]);
            } else {
                ((TextView) view.findViewById(R.id.tv_tab_name)).setTextColor(Color.BLACK);
                imageView.setImageResource(TabDB.getTabsImg()[i]);
            }
        }
    }
}
