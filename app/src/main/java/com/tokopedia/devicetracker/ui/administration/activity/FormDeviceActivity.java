package com.tokopedia.devicetracker.ui.administration.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.BaseActivity;

public class FormDeviceActivity extends BaseActivity {

    private static final String EXTRA_DEVICE_DATA = "EXTRA_DEVICE_DATA";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_form_device;
    }


    public static Intent factoryIntentUpdate(Activity activity, DeviceData deviceData) {
        Intent intent = new Intent(activity, FormDeviceActivity.class);
        intent.putExtra(EXTRA_DEVICE_DATA, deviceData);
        return intent;
    }

    public static Intent factoryIntentAdd(Activity activity) {
        return new Intent(activity, FormDeviceActivity.class);
    }
}
