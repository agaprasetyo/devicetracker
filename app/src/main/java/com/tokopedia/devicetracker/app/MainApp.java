package com.tokopedia.devicetracker.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tokopedia.devicetracker.database.DbService;
import com.tokopedia.devicetracker.di.component.DaggerMainAppComponent;
import com.tokopedia.devicetracker.di.component.MainAppComponent;
import com.tokopedia.devicetracker.di.module.ApplicationModule;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class MainApp extends MultiDexApplication {
    private static final String TAG = MainApp.class.getSimpleName();
    private static MainApp mInstance;
    private ImageLoader mImageLoader;
    @Inject
    DbService dbService;
    private MainAppComponent mainAppComponent;

    public static synchronized MainApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainAppComponent = DaggerMainAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        // mainAppComponent.inject(this);
        mainAppComponent.inject(this);
        mInstance = this;
        initialImageLoader();
        //   initialDbService();
    }

    public static MainAppComponent getComponent(Context context) {
        return ((MainApp) context.getApplicationContext()).mainAppComponent;
    }

    public static MainAppComponent getComponent() {
        return  getInstance().mainAppComponent;
    }


    private void initialDbService() {
        this.dbService = new DbService(this);
    }

    private void initialImageLoader() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(this, "devicetracking/cache/chosephoto");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .diskCacheFileCount(1000)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config);
    }

    public DbService getDbService() {
        return dbService;
    }
}
