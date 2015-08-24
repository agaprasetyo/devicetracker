package com.tokopedia.devicetracker.ui;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.DbService;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    @Inject
    DbService dbService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayoutId());
        MainApp.getComponent(this).inject(this);
        injectViews();
    }

    protected abstract int getResourceLayoutId();

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
