package com.mac.nytimes.network;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.mac.nytimes.commons.AppConstants;
import com.mac.nytimes.dto.TopStoriesResponse;
import com.mac.nytimes.interfaces.RestAPIListener;
import com.mac.nytimes.network.services.NewsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NYTimesRestAPI implements Callback<TopStoriesResponse> {
    private final RestAPIListener listener;
    private NewsService service;

    public NYTimesRestAPI(RestAPIListener listener) {
        this.listener = listener;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        service = retrofit.create(NewsService.class);
    }

    public void getTopStories() {
        Call<TopStoriesResponse> call = service.getTopStories(AppConstants.API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<TopStoriesResponse> call, Response<TopStoriesResponse> response) {
        int statusCode = response.code();
        TopStoriesResponse responseObj = response.body();
        Log.e(this.getClass().getSimpleName(), response.toString());
        listener.onSuccess(responseObj);
    }

    @Override
    public void onFailure(Call<TopStoriesResponse> call, Throwable t) {
        listener.onFailure(t.getLocalizedMessage());
    }

}
