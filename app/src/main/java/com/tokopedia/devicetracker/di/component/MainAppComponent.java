package com.tokopedia.devicetracker.di.component;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.di.dagger.PerApp;
import com.tokopedia.devicetracker.di.module.ApplicationModule;
import com.tokopedia.devicetracker.di.module.DbOrmModule;
import com.tokopedia.devicetracker.ui.BaseActivity;

import dagger.Component;

/**
 * Created by Angga.Prasetiyo on 20/08/2015.
 */
@PerApp
@Component(modules = {
        ApplicationModule.class,
        DbOrmModule.class
})
public interface MainAppComponent {
    void inject(MainApp application);

    void inject(BaseActivity activity);

    void inject(Object object);


}
