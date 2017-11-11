package com.danirosas.itunesintegrationapp.presenter;

import com.danirosas.itunesintegrationapp.events.AlbumEvent;
import com.danirosas.itunesintegrationapp.events.SongsEvent;

/**
 * Created by DaniRosas on 9/11/17.
 */

public interface ItunesPresenter {

    void onCreate();
    void onPause();
    void onDestroy();

    void onEventMainThread(AlbumEvent event);
    void onEventMainThread(SongsEvent event);

    void getAlbums();
    void getSongs(String id);
}
