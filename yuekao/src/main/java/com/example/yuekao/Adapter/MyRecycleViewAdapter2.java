package com.example.yuekao.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuekao.JavaBean.Bean2;
import com.example.yuekao.R;
import com.example.yuekao.Utils.ImageLoaderUtils;

/**
 * @author lvhongyang.
 * @time 2017/9/22.
 * @desc:
 */

public class MyRecycleViewAdapter2 extends RecyclerView.Adapter<MyRecycleViewAdapter2.MyViewHolder2> implements View.OnClickListener {
    private Bean2 bean;
    private Context context;

    public MyRecycleViewAdapter2(Bean2 bean, Context context) {
        this.bean = bean;
        this.context = context;
    }

    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.griditem, null);
        MyViewHolder2 myViewHolder2 = new MyViewHolder2(inflate);
        inflate.setOnClickListener(this);
        return myViewHolder2;
    }

    @Override
    public void onClick(View view) {
        if (mMyItemclickListener != null) {
            mMyItemclickListener.itemclick(view, (Integer) view.getTag());
        }
    }

    public interface MyItemclickListener {
        void itemclick(View view, int position);
    }

    public MyItemclickListener mMyItemclickListener;

    public void setmMyItemclickListener(MyItemclickListener mMyItemclickListener) {
        this.mMyItemclickListener = mMyItemclickListener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder2 holder, int position) {
        holder.itemView.setTag(position);
        ImageLoaderUtils.setImageView(bean.others.get(position).thumbnail,context,holder.image2);
        holder.tv2.setText(bean.others.get(position).name);
    }


    @Override
    public int getItemCount() {
        return bean.others.size();
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {


        private final ImageView image2;
        private final TextView tv2;

        public MyViewHolder2(View itemView) {
            super(itemView);
            image2 = itemView.findViewById(R.id.image2);
            tv2 = itemView.findViewById(R.id.tv2);
        }
    }


}
