package com.danirosas.itunesintegrationapp.repository;

import com.danirosas.itunesintegrationapp.model.ApiAlbumResponse;
import com.danirosas.itunesintegrationapp.model.ApiSongsResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DaniRosas on 10/11/17.
 */

public class ItunesRestServiceHelper {

    private static final String URL = "https://itunes.apple.com/";

    private static OkHttpClient httpClient = new OkHttpClient();
    private static ItunesRestServiceHelper instance = new ItunesRestServiceHelper();
    private ItunesService service;


    private ItunesRestServiceHelper() {

        Retrofit retrofit = create().build();
        service = retrofit.create(ItunesService.class);
    }



    public static ItunesRestServiceHelper getInstance() {
        return instance;
    }

    private Retrofit.Builder create() {

        return new Retrofit.Builder()
                .baseUrl(URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create());
    }


    public Call<ApiAlbumResponse> getAlbums(){
        return service.getAlbums();
    }

    public Call<ApiSongsResponse> getSongs(String id) {
        return service.getSongs(id);
    }

    public interface ItunesService {

        @GET("lookup?id=909253&entity=album")
        Call<ApiAlbumResponse> getAlbums(
        );

        @GET("lookup?entity=song")
        Call<ApiSongsResponse> getSongs(@Query("id") String id);
    }
}
