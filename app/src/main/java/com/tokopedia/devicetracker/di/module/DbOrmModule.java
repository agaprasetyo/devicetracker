package com.tokopedia.devicetracker.di.module;

import android.content.Context;

import com.tokopedia.devicetracker.database.DbService;
import com.tokopedia.devicetracker.di.dagger.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
@Module
public class DbOrmModule {
    private static final String TAG = DbOrmModule.class.getSimpleName();

    @Provides
    @PerApp
    DbService provideBundleDbService(Context context) {
        return new DbService(context);
    }
}
