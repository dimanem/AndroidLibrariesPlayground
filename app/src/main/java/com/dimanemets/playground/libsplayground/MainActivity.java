package com.dimanemets.playground.libsplayground;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dimanemets.playground.libsplayground.api.GithubService;
import com.dimanemets.playground.libsplayground.api.GithubUser;
import com.dimanemets.playground.libsplayground.presenter.UserPresenter;
import com.dimanemets.playground.libsplayground.view.UserView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dima.dagger2tut1.R;


public class MainActivity extends AppCompatActivity implements UserView {

    private static final String TAG = "MainActivity";

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    GithubService service;

    @BindView(R.id.etSearchUser) EditText editTextSearchUser;

    UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MyApplication)getApplication()).getApplicationComponent().inject(this);

        ButterKnife.bind(this);

        editTextSearchUser.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideKeyboard(v);
                    loadUser(editTextSearchUser.getText().toString());
                    return true;
                }
                return false;
            }
        });

        userPresenter = new UserPresenter(sharedPreferences, service);
        userPresenter.setUserView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        userPresenter.onStop();
    }

    @Override
    public void showUser(GithubUser user) {
        Toast.makeText(this, "User: " + user.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_LONG).show();
    }

    private void loadUser(String userName) {
        Log.i(TAG, "Loading user: " + userName);
        userPresenter.loadUser(userName);
    }

    private void hideKeyboard(TextView v) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
