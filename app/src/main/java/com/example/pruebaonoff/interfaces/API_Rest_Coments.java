package com.example.pruebaonoff.interfaces;

import com.example.pruebaonoff.elementos.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Rest_Coments {
    String route = "/comments";

    @GET(route)
    Call<List<Post>> getPost();
}
