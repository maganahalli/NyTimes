package com.mac.nytimes.adapters;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mac.nytimes.R;
import com.mac.nytimes.models.Result;
import com.mac.nytimes.viewholders.NewsViewHolder;


public class NewsAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private List<Result> mItems;
    private boolean grid;

    public NewsAdapter(Context context, List<Result> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.card_view, viewGroup, false);
        return new NewsViewHolder(mContext, v);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((NewsViewHolder) viewHolder).invalidate(mItems.get(i));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addAll(List<Result> items) {
        mItems = items;
    }

    public List<Result> getItems() {
        return mItems;
    }

}
