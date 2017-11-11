package com.danirosas.itunesintegrationapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DaniRosas on 9/11/17.
 */

public class ApiAlbumResponse {
    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<Album> albums = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlba(List<Album> alba) {
        this.albums = alba;
    }
}
