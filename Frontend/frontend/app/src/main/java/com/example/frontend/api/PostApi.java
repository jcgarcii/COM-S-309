package com.example.frontend.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi {

    @GET("posts/1")
    Call<PostApi> getFirstPost();
}

