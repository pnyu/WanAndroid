package com.bamboo.pengyu.wanandroid;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

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
        return new View(this);
    }

    @Override
    public void onTabChanged(String tabId) {

    }
}
