package com.danii.dihub;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface GithubAPI {
    @GET("/users/{username}/repos")
    Call<List<GithubRepo>> getUser(@Path("username") String username, @Query("type") String reptypes, @Query("sort") String sort);

}



