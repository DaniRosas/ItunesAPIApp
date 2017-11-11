package com.danirosas.itunesintegrationapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danirosas.itunesintegrationapp.R;
import com.danirosas.itunesintegrationapp.model.Songs;

import java.util.List;

/**
 * Created by DaniRosas on 11/11/17.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.myViewHolder> {

    private List<Songs> songsList;
    private Context mContext;

    public SongsAdapter(List<Songs> songsList, Context mContext) {
        this.songsList = songsList;
        this.mContext = mContext;
    }

    @Override
    public SongsAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new myViewHolder(view);    }

    @Override
    public void onBindViewHolder(SongsAdapter.myViewHolder holder, int position) {
        Songs song = songsList.get(position);

        holder.song.setText(song.getTrackName());

    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        private TextView song;

        public myViewHolder(View itemView) {
            super(itemView);

            song = itemView.findViewById(R.id.songName);
        }
    }
}
