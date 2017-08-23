package com.dimanemets.playground.libsplayground.view;

import com.dimanemets.playground.libsplayground.api.GithubUser;

/**
 * Created by dimanemets on 22/08/2017.
 */

public interface UserView {
    void showUser(GithubUser user);
    void showError(String error);
}
