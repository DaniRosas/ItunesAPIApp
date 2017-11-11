package com.danirosas.itunesintegrationapp.view.fragment;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.danirosas.itunesintegrationapp.R;
import com.danirosas.itunesintegrationapp.listeners.OnClickAlbumListener;
import com.danirosas.itunesintegrationapp.model.Album;
import com.danirosas.itunesintegrationapp.model.ApiAlbumResponse;
import com.danirosas.itunesintegrationapp.model.ApiSongsResponse;
import com.danirosas.itunesintegrationapp.presenter.ItunesPresenterImpl;
import com.danirosas.itunesintegrationapp.view.AlbumsView;
import com.danirosas.itunesintegrationapp.view.adapter.AlbumsAdapter;
import com.danirosas.itunesintegrationapp.view.adapter.SongsAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment implements AlbumsView, OnClickAlbumListener{

    private ItunesPresenterImpl presenter;
    private ApiAlbumResponse apiAlbumResponse;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private AlbumsAdapter adapter;
    private Context mContext;
    private Activity mActivity;


    public AlbumsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenterIncialization();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        mContext = getActivity().getApplicationContext();
        mActivity = getActivity();

        bindViews(view);

        initiAdapter();

        presenter.getAlbums();

        return view;
    }

    private void initiAdapter() {
        adapter = new AlbumsAdapter(new ArrayList<Album>(), mContext, this);
        setUpRecyclerview();
    }

    private void setUpRecyclerview() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void presenterIncialization() {
        presenter = new ItunesPresenterImpl(this);
        presenter.onCreate();
    }

    private void bindViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
    }

    public static AlbumsFragment newInstance() {
        return new AlbumsFragment();
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showList(int type) {
         Toast.makeText(getActivity().getApplicationContext(), String.valueOf(type), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAlbums(ApiAlbumResponse apiAlbumResponse) {
        this.apiAlbumResponse = apiAlbumResponse;
        adapter.setAlbums(apiAlbumResponse.getAlbums());
    }

    @Override
    public void setSongs(ApiSongsResponse apiSongsResponse) {
        showTracksDialog(apiSongsResponse);
    }

    public void showTracksDialog(ApiSongsResponse apiSongsResponse) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = this.mActivity.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_tracks,null);

        TextView textView = v.findViewById(R.id.title);
        ImageButton imageButton = v.findViewById(R.id.closeButton);
        RecyclerView rv = v.findViewById(R.id.recyclerviewSongs);

        textView.setText(apiSongsResponse.getSongsList().get(0).getCollectionName());

        SongsAdapter songsAdapter = new SongsAdapter(apiSongsResponse.getSongsList(), mContext);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(songsAdapter);

        builder.setView(v);
        final AlertDialog dialog = builder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onClickAlbum(String id) {
        presenter.getSongs(id);
    }
}
