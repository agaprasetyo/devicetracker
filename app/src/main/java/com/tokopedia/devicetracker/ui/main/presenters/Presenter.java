package com.tokopedia.devicetracker.ui.main.presenters;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public abstract class Presenter {
    private static final String TAG = Presenter.class.getSimpleName();


    public abstract void initialize();

    public abstract void resume();

    public abstract void pause();
}
