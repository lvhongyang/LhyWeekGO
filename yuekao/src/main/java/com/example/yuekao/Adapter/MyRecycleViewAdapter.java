package com.example.yuekao.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuekao.JavaBean.Bean;
import com.example.yuekao.R;
import com.example.yuekao.Utils.GlideImageLoader;
import com.example.yuekao.Utils.ImageLoaderUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvhongyang.
 * @time 2017/9/22.
 * @desc:
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {
    private Bean bean;
    private Context context;
    private List<String> listImages=new ArrayList<>();

    public MyRecycleViewAdapter(Bean bean, Context context) {
        this.bean = bean;
        this.context = context;

        //头部图片设置资源
        List<Bean.TopStoriesBean> top_stories = bean.top_stories;
        List<Bean.StoriesBean> stories = bean.stories;
        listImages.add(top_stories.get(0).image);
        listImages.add(stories.get(0).images.get(0));
    }


    //绑定数据
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch(viewType){
            case Type:
                View inflate = View.inflate(context, R.layout.headeriew, null);
                return new MyViewHolder(inflate);
            case Type2:
                View inflate2 = View.inflate(context, R.layout.item1, null);
                return new MyViewHolder(inflate2);
            default:
                break;
        }
        return null;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int type = getItemViewType(position);
        if(type==Type){
            holder.banner.setImageLoader(new GlideImageLoader())
                    .setDelayTime(3000)
                    .setImages(listImages)
                    .start();
        }else{
            ImageLoaderUtils.setImageView(bean.top_stories.get((position+1)%(bean.top_stories.size())).image,context,holder.image1);
            holder.tv1.setText(bean.top_stories.get((position+1)%(bean.top_stories.size())).title);
        }

    }

    //获取他的类型码
    public final int Type = 555;
    public final int Type2 = 666;


    //选择它的多条目
    @Override
    public int getItemViewType(int position) {

        if (position==0) {
            return Type;
        }  else {
            return Type2;
        }
    }


    @Override
    public int getItemCount() {
        return bean.stories.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        private final Banner banner;
        private final ImageView image1;
        private final TextView tv1;

        public MyViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
            image1 = itemView.findViewById(R.id.image1);
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }


}
