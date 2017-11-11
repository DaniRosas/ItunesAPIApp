package com.danirosas.itunesintegrationapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danirosas.itunesintegrationapp.R;
import com.danirosas.itunesintegrationapp.listeners.OnClickAlbumListener;
import com.danirosas.itunesintegrationapp.model.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by DaniRosas on 11/11/17.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.myViewHolder> {

    private List<Album> albumsList;
    private Context mContext;
    private OnClickAlbumListener listener;

    public AlbumsAdapter(List<Album> albumsList, Context mContext, OnClickAlbumListener onClickAlbumListener) {
        this.albumsList = albumsList;
        this.mContext = mContext;
        this.listener = onClickAlbumListener;
    }

    @Override
    public AlbumsAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new myViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(AlbumsAdapter.myViewHolder holder, int position) {

        Album album = albumsList.get(position);

        holder.name.setText(album.getArtistName());

        if (album.getCollectionName() == null) {
            holder.albumName.setText("Unknown");
        } else {
            holder.albumName.setText(album.getCollectionName());
        }

        if (album.getTrackCount() != null) {
            holder.numOfTracks.setText(String.format("%s canciones", String.valueOf(album.getTrackCount())));
        }

        Picasso.with(holder.imageview.getContext()).load(album.getArtworkUrl100()).into(holder.imageview);

        holder.setClickListener(album, listener);

    }

    @Override
    public int getItemCount() {
        return albumsList.size();
    }

    public void setAlbums(List<Album> albums) {
        this.albumsList.addAll(albums);
        notifyDataSetChanged();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageview;
        private TextView name, albumName, numOfTracks;
        private View itemView;

        public myViewHolder(Context context, View itemView) {
            super(itemView);

            this.itemView = itemView;
            imageview = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.artistNameTextview);
            albumName = itemView.findViewById(R.id.albumNameTextview);
            numOfTracks = itemView.findViewById(R.id.numTracksTextview);

        }


        public void setClickListener(final Album album,
                                     final OnClickAlbumListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickAlbum(String.valueOf(album.getCollectionId()));
                }
            });
        }
    }
}
