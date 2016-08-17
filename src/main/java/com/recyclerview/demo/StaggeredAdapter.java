package com.recyclerview.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEO on 2016/8/13.
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private List<Integer> value;
    private boolean vertical;

    public StaggeredAdapter(Context context, List<String> datas, boolean vertical) {
        this.mContext = context;
        this.mDatas = datas;
        this.vertical = vertical;
        mInflater = LayoutInflater.from(context);
            value = new ArrayList<>();
            for (int i = 0; i < mDatas.size(); i++) {
                value.add((int) (100 + Math.random() * 300));
            }
    }


    @Override
    public StaggeredAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        StaggeredAdapter.MyViewHolder viewHolder = new StaggeredAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (vertical) {
            lp.height = value.get(position);
        }else{
            lp.width = value.get(position);
        }
        holder.itemView.setLayoutParams(lp);
        holder.tv.setText(mDatas.get(position));
        if (mOnItemClickListener == null) {
            return;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.itemView, position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemClickListener.onItemLongClick(holder.itemView, position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_recyclerview_tv);
        }
    }
}

