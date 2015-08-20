package com.tokopedia.devicetracker.di.component;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.ui.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Angga.Prasetiyo on 20/08/2015.
 */
@Singleton
@Component()
public interface MainAppComponent {
    void inject(MainApp application);

    void inject(BaseActivity activity);
}
