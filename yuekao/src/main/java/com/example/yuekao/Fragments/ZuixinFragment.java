package com.example.yuekao.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuekao.Adapter.MyRecycleViewAdapter;
import com.example.yuekao.JavaBean.Bean;
import com.example.yuekao.R;
import com.example.yuekao.Utils.EndlessRecyclerOnScrollListener;
import com.example.yuekao.Utils.OkHttpManager;
import com.google.gson.Gson;

/**
 * @author lvhongyang.
 * @time 2017/9/22.
 * @desc:
 */

public class ZuixinFragment extends Fragment {

    private MyRecycleViewAdapter myRecycleViewAdapter;
    private View view;
    private RecyclerView mRecycler1;
    private OkHttpManager okHttpManager;
    private String path = "http://news-at.zhihu.com/api/4/news/latest";
    private SwipeRefreshLayout mSwipRefreshLayout;
    private Bean bean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zuixin, container, false);
        okHttpManager = OkHttpManager.getInstance();
        RequestData();

        initView(view);
        return view;
    }

    /**
     * 请求数据
     */
    private void RequestData() {
        okHttpManager.asyncJsonStringByURL(path, new OkHttpManager.Function1() {

            @Override
            public void onResponse(String result) {
                bean = new Gson().fromJson(result, Bean.class);
                System.out.println(bean.stories.size());

                myRecycleViewAdapter = new MyRecycleViewAdapter(bean, getContext());
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                mRecycler1.setLayoutManager(linearLayoutManager);
                mRecycler1.setAdapter(myRecycleViewAdapter);
                mSwipRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    public void onRefresh() {
                        //我在List最前面加入一条数据
                        bean.stories.add(0, bean.stories.get(2));
                        bean.top_stories.add(0, bean.top_stories.get(2));
                        //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
                        myRecycleViewAdapter.notifyDataSetChanged();
                        mSwipRefreshLayout.setRefreshing(false);
                    }
                });
                mRecycler1.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int currentPage) {
                        for (int i = 0; i < 3; i++) {
                            bean.top_stories.add(bean.top_stories.get(i));
                            bean.stories.add(bean.stories.get(i));
                        }
                        myRecycleViewAdapter.notifyDataSetChanged();
                    }
                });

            }
        });

    }



    private void initView(View view) {
        mRecycler1 = (RecyclerView) view.findViewById(R.id.recycler1);
        mSwipRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipRefreshLayout);
    }
}
