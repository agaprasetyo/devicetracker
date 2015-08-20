package com.tokopedia.devicetracker.di.module;

import android.app.Application;
import android.content.Context;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.di.dagger.PerApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Angga.Prasetiyo on 20/08/2015.
 */
@Module
public class ApplicationModule {
    private final MainApp application;


    public ApplicationModule(MainApp mainApp) {
        this.application = mainApp;

    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return application;
    }

}
