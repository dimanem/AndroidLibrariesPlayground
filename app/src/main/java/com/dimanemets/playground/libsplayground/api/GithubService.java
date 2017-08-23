package com.dimanemets.playground.libsplayground.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by dimanemets on 22/08/2017.
 */

public interface GithubService {
    @GET("users/{username}")
    Observable<GithubUser> getGithubUser(@Path("username") String username);
}
