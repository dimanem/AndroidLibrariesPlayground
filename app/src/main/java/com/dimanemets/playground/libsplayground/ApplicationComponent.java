package com.dimanemets.playground.libsplayground;

import javax.inject.Singleton;

import dagger.Component;
import com.dimanemets.playground.libsplayground.MainActivity;
import com.dimanemets.playground.libsplayground.APIModule;
import com.dimanemets.playground.libsplayground.ApplicationModule;

/**
 * Created by dimanemets on 22/08/2017.
 */

@Singleton
@Component(modules={ApplicationModule.class, APIModule.class})
public interface ApplicationComponent {
    void inject(MainActivity target);
}
