package com.danirosas.itunesintegrationapp.presenter;

import com.danirosas.itunesintegrationapp.events.AlbumEvent;
import com.danirosas.itunesintegrationapp.events.SongsEvent;
import com.danirosas.itunesintegrationapp.libs.base.Eventbus;
import com.danirosas.itunesintegrationapp.libs.base.GreenRobotEventBus;
import com.danirosas.itunesintegrationapp.repository.ItunesRepository;
import com.danirosas.itunesintegrationapp.repository.ItunesRepositoryImpl;
import com.danirosas.itunesintegrationapp.view.AlbumsView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by DaniRosas on 9/11/17.
 */

public class ItunesPresenterImpl implements ItunesPresenter{

    private Eventbus eventBus;
    private AlbumsView view;
    private ItunesRepository repository;

    public ItunesPresenterImpl(AlbumsView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        this.repository = new ItunesRepositoryImpl();

    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AlbumEvent event) {

        switch (event.getType()){
            case AlbumEvent.READ_ALBUMS_EVENT:
                view.hideProgress();
                view.showList();

                view.setAlbums(event.getApiAlbumResponse());
                break;
            default:

                break;
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(SongsEvent event) {
        switch (event.getType()){
            case SongsEvent.READ_SONGS_EVENT:
                view.setSongs(event.getApiSongsResponse());
                break;
            default:

                break;
        }
    }

    @Override
    public void getAlbums() {
        if(view != null){
            view.showProgress();
            view.hideList();
        }
        repository.getAlbums();

    }

    @Override
    public void getSongs(String id) {
        repository.getSongs(id);
    }
}
