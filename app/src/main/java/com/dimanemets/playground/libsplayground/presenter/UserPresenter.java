package com.dimanemets.playground.libsplayground.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import com.dimanemets.playground.libsplayground.api.GithubService;
import com.dimanemets.playground.libsplayground.view.UserView;
import com.dimanemets.playground.libsplayground.api.GithubUser;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dimanemets on 22/08/2017.
 */

public class UserPresenter {

    private static final String TAG = "UserPresenter";

    private SharedPreferences sharedPreferences;
    private GithubService githubService;
    private UserView userView;

    private CompositeSubscription subscriptions;

    // Constructor injections
    public UserPresenter(SharedPreferences sharedPreferences, GithubService githubService) {
        this.sharedPreferences = sharedPreferences;
        this.githubService = githubService;
        this.subscriptions = new CompositeSubscription();
    }

    // Method injection
    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public void loadUser(final String userName) {
        Subscription subscription = githubService.getGithubUser(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GithubUser>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getLocalizedMessage());
                        userView.showError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(GithubUser user) {
                        // TODO this line is just for demo
                        // Just to use shared prefs dependency
                        sharedPreferences.edit().putString(userName, new Gson().toJson(user));
                        userView.showUser(user);
                    }
                });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
