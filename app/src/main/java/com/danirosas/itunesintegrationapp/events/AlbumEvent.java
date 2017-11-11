package com.danirosas.itunesintegrationapp.events;

import com.danirosas.itunesintegrationapp.model.ApiAlbumResponse;

/**
 * Created by DaniRosas on 10/11/17.
 */

public class AlbumEvent {

    private int type;
    private String error;
    private ApiAlbumResponse apiAlbumResponse;

    public final static int READ_ALBUMS_EVENT = 0;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String showEvent(String s){ return s;}

    public void setResponse(ApiAlbumResponse response) {
        this.apiAlbumResponse = response;
    }

    public ApiAlbumResponse getApiAlbumResponse() {
        return apiAlbumResponse;
    }
}
