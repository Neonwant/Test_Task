package com.softgroup.vkplayer.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softgroup.vkplayer.R;
import com.softgroup.vkplayer.data.PopularListItem;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private ArrayList<PopularListItem> mData;

    public PopularAdapter(ArrayList<PopularListItem> items) {
        this.mData = items;
    }

    @Override
    public PopularAdapter.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopularViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_music, parent, false));
    }

    @Override
    public void onBindViewHolder(PopularAdapter.PopularViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public PopularViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }


}
