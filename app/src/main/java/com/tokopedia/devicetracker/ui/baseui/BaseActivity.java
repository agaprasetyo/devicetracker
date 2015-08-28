package com.tokopedia.devicetracker.ui.baseui;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public abstract class BaseActivity<P> extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    public static final int REQUEST_ADD_DEVICE = 1000;
    public static final int REQUEST_EDIT_DEVICE = 1001;
    public static final int REQUEST_CAMERA = 1002;
    public static final int REQUEST_LOG_DEVICE = 1003;
    public static final int REQUEST_ADMIN_OPERATION = 1004;


    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialPresenter();
        setContentView(getResourceLayoutId());
        injectViews();
    }

    protected abstract void initialPresenter();

    protected abstract
    @LayoutRes
    int getResourceLayoutId();

    private void injectViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
