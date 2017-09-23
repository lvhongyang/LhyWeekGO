package com.example.yuekao;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.yuekao.Adapter.MyAdapter;
import com.example.yuekao.Fragments.ThemeFragment;
import com.example.yuekao.Fragments.ZuixinFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mVp;
    private List<Fragment> listFrag=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        for (int i = 0; i <3 ; i++) {
            listFrag.add(new ZuixinFragment());
        }
        listFrag.add(new ThemeFragment());

        for (int i = 0; i < 4; i++) {
            mTab.addTab(mTab.newTab());
        }
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        myAdapter.setFragList(listFrag);
        mVp.setAdapter(myAdapter);
        //设置标题

        mTab.getTabAt(0).setText("最新日报");
        mTab.getTabAt(1).setText("专栏");
        mTab.getTabAt(2).setText("热门");
        mTab.getTabAt(3).setText("主题日报");

    }
    //找控件
    private void initView() {
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        //关联TabLayout和ViewPager
        mTab.setupWithViewPager(mVp);
    }

}
