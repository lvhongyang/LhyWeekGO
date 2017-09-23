package com.example.yuekao.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuekao.Adapter.MyRecycleViewAdapter2;
import com.example.yuekao.JavaBean.Bean2;
import com.example.yuekao.Main2Activity;
import com.example.yuekao.R;
import com.example.yuekao.Utils.OkHttpManager;
import com.google.gson.Gson;

/**
 * @author lvhongyang.
 * @time 2017/9/22.
 * @desc:
 */

public class ThemeFragment  extends Fragment {

    private String path="http://news-at.zhihu.com/api/4/themes";
    private View view;
    private RecyclerView mRecycler2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme, container, false);
        initView(view);
        requestData();
        return view;
    }

    private void requestData() {
        OkHttpManager okHttpManager = OkHttpManager.getInstance();
        okHttpManager.asyncJsonStringByURL(path, new OkHttpManager.Function1() {
            @Override
            public void onResponse(String result) {
                final Bean2 bean2 = new Gson().fromJson(result, Bean2.class);

                mRecycler2.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
                MyRecycleViewAdapter2 adapter2 = new MyRecycleViewAdapter2(bean2, getContext());
                mRecycler2.setAdapter(adapter2);
                adapter2.setmMyItemclickListener(new MyRecycleViewAdapter2.MyItemclickListener() {
                    @Override
                    public void itemclick(View view, int position) {
                        Intent intent = new Intent(getActivity(), Main2Activity.class);

                        Bundle bundle=new Bundle();
                        bundle.putSerializable("name", bean2);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void initView(View view) {
        mRecycler2 = (RecyclerView) view.findViewById(R.id.recycler2);
    }
}
