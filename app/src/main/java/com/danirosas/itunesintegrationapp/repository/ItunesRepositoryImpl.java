package com.danirosas.itunesintegrationapp.repository;

import android.content.res.Resources;

import com.danirosas.itunesintegrationapp.R;
import com.danirosas.itunesintegrationapp.events.AlbumEvent;
import com.danirosas.itunesintegrationapp.events.SongsEvent;
import com.danirosas.itunesintegrationapp.libs.base.Eventbus;
import com.danirosas.itunesintegrationapp.libs.base.GreenRobotEventBus;
import com.danirosas.itunesintegrationapp.model.ApiAlbumResponse;
import com.danirosas.itunesintegrationapp.model.ApiSongsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DaniRosas on 9/11/17.
 */

public class ItunesRepositoryImpl implements ItunesRepository {

    private final ItunesRestServiceHelper serviceHelper;

    public ItunesRepositoryImpl() {
        serviceHelper = ItunesRestServiceHelper.getInstance();
    }

    @Override
    public ApiAlbumResponse getAlbums() {

        Call<ApiAlbumResponse> call = serviceHelper.getAlbums();
        call.enqueue(new Callback<ApiAlbumResponse>() {
            @Override
            public void onResponse(Call<ApiAlbumResponse> call, Response<ApiAlbumResponse> response) {
                switch (response.code()){
                    case 200:
                        postEvent(response.body());
                        break;
                    default:
                        postEvent(Resources.getSystem().getString(R.string.albums_download_error_message));
                        break;
                }

            }

            @Override
            public void onFailure(Call<ApiAlbumResponse> call, Throwable t) {

            }
        });
        return null;
    }

    @Override
    public void getSongs(String id) {
        Call<ApiSongsResponse> call = serviceHelper.getSongs(id);
        call.enqueue(new Callback<ApiSongsResponse>() {
            @Override
            public void onResponse(Call<ApiSongsResponse> call, Response<ApiSongsResponse> response) {
                switch (response.code()){
                    case 200:
                        postEvent(response.body());
                        break;
                    default:
                        postEvent(Resources.getSystem().getString(R.string.albums_download_error_message));
                        break;
                }
            }

            @Override
            public void onFailure(Call<ApiSongsResponse> call, Throwable t) {

            }
        });
    }

    private void postEvent(ApiSongsResponse apiSongsResponse) {
        SongsEvent event = new SongsEvent();
        event.setResponse(apiSongsResponse);
        event.setType(SongsEvent.READ_SONGS_EVENT);
        Eventbus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);
    }

    private void postEvent(String hola) {
        AlbumEvent event = new AlbumEvent();
        event.showEvent(hola);

        Eventbus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);
    }

    private void postEvent(ApiAlbumResponse apiAlbumResponse){
        AlbumEvent event = new AlbumEvent();
        event.setResponse(apiAlbumResponse);
        event.setType(AlbumEvent.READ_ALBUMS_EVENT);
        Eventbus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);
    }
}
