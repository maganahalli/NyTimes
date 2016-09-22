package com.mac.nytimes.network.services;


import com.mac.nytimes.dto.TopStoriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    @GET("svc/topstories/v2/home.json")
    Call<TopStoriesResponse> getTopStories(@Query("api-key") String API_KEY);
}
