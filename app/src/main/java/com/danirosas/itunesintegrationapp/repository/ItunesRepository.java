package com.danirosas.itunesintegrationapp.repository;

import com.danirosas.itunesintegrationapp.model.ApiAlbumResponse;

/**
 * Created by DaniRosas on 9/11/17.
 */

public interface ItunesRepository {
    ApiAlbumResponse getAlbums();

    void getSongs(String id);
}
