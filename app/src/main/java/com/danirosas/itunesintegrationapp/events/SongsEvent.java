package com.danirosas.itunesintegrationapp.events;

import com.danirosas.itunesintegrationapp.model.ApiSongsResponse;

/**
 * Created by DaniRosas on 11/11/17.
 */

public class SongsEvent {
    private int type;
    private String error;
    private ApiSongsResponse apiSongsResponse;

    public final static int READ_SONGS_EVENT = 0;

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

    public void setResponse(ApiSongsResponse response) {
        this.apiSongsResponse = response;
    }

    public ApiSongsResponse getApiSongsResponse() {
        return apiSongsResponse;
    }
}
