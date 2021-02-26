package com.example.pruebaonoff.interfaces;

import com.example.pruebaonoff.elementos.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Rest_Post {
    String route = "/posts";

    @GET(route)
    Call<List<Post>> getPost();
}
