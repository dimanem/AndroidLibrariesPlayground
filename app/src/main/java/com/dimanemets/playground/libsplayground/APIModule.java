package com.dimanemets.playground.libsplayground;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import com.dimanemets.playground.libsplayground.api.GithubService;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dimanemets on 22/08/2017.
 */

@Module
public class APIModule {

    private String baseUrl;

    public APIModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    GithubService provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

    @Provides
    Retrofit provideRetrofit(GsonConverterFactory converterFactory,
                                        OkHttpClient client,
                                        CallAdapter.Factory factory) {
        return new Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .client(client)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(factory)
                .build();
    }

    @Provides
    OkHttpClient provideOkHTTPClient(Application application) {
        // Enable caching for OkHttp
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new OkHttpClient().newBuilder()
                .cache(new Cache(application.getCacheDir(), cacheSize))
                .build();
    }

    @Provides
    CallAdapter.Factory provideCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    GsonConverterFactory providerGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }
}
