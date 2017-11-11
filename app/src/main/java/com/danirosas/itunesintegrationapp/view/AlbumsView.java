package com.danirosas.itunesintegrationapp.view;

import com.danirosas.itunesintegrationapp.model.ApiAlbumResponse;
import com.danirosas.itunesintegrationapp.model.ApiSongsResponse;

/**
 * Created by DaniRosas on 9/11/17.
 */

public interface AlbumsView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void showList(int type);
    void setAlbums(ApiAlbumResponse apiAlbumResponse);

    void setSongs(ApiSongsResponse apiSongsResponse);
}
