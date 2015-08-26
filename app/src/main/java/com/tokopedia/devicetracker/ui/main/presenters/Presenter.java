package com.tokopedia.devicetracker.ui.main.presenters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public abstract class Presenter {
    private static final String TAG = Presenter.class.getSimpleName();

    public abstract void initialize();

    public abstract void resume();

    public abstract void pause();

    public abstract void stop();

    public abstract void start();

    public abstract void optionsItemSelected(MenuItem item);

    public abstract void activityResult(int requestCode, int resultCode, Intent data);

    public DisplayImageOptions getDisplayImage() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .displayer(new FadeInBitmapDisplayer(1000))
                .build();
    }
}
