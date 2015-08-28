package com.tokopedia.devicetracker.ui.mainadmin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.ui.baseui.BaseActivity;
import com.tokopedia.devicetracker.ui.mainadmin.adapter.DeviceLogRecyclerAdapter;
import com.tokopedia.devicetracker.ui.mainadmin.presenters.TrackingLogDevicePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public class TrackingLogDeviceActivity extends BaseActivity<TrackingLogDevicePresenter> implements TrackingLogDevicePresenter.View {

    public static final String EXTRA_DEVICE_DATA = "EXTRA_DEVICE_DATA";

    @Bind(R.id.list)
    RecyclerView recyclerView;

    private DeviceLogRecyclerAdapter adapter;

    public static Intent factoryIntent(Activity activity, DeviceData deviceData) {
        Intent intent = new Intent(activity, TrackingLogDeviceActivity.class);
        intent.putExtra(EXTRA_DEVICE_DATA, deviceData);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initialize();
        presenter.initialBundleData(getIntent().getExtras());
    }

    @Override
    protected void initialPresenter() {
        presenter = new TrackingLogDevicePresenter(this, this);
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_tracking_log_device;
    }


    @Override
    public void setAttributeVar() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DeviceLogRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void repopulateRecycler() {

    }

    @Override
    public void populateRecycler(List<TrackingData> successResultObj) {
        adapter.setData(successResultObj);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
