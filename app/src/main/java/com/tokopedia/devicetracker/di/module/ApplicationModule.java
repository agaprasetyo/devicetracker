package com.tokopedia.devicetracker.di.module;

import android.app.Application;
import android.content.Context;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.di.dagger.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Angga.Prasetiyo on 20/08/2015.
 */
@Module
public class ApplicationModule {
    private final MainApp app;

    public ApplicationModule(MainApp mainApp) {
        this.app = mainApp;

    }

    @Provides
    @PerApp
    MainApp provideNowDoThisApp() {
        return app;
    }


    @Provides
    @PerApp
    Context provideAppContext() {
        return app.getApplicationContext();
    }

    @Provides
    @PerApp
    Application provideApplication(MainApp app) {
        return app;
    }


}
