package com.tokopedia.devicetracker.ui.administration.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.BaseActivity;
import com.tokopedia.devicetracker.ui.administration.fragment.DeviceRecyclerAdminFragment;
import com.tokopedia.devicetracker.ui.administration.presenters.MainAdminPresenter;

public class MainAdminActivity extends BaseActivity implements DeviceRecyclerAdminFragment.ActivityInteractionListener
        , MainAdminPresenter.View {

    private MainAdminPresenter presenter;

    public static Intent factoryIntent(Activity activity) {
        return new Intent(activity, MainAdminActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new MainAdminPresenter(this, this);
        presenter.initialize();
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_main_admin;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.optionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setAttributeVar() {

    }

    @Override
    public void navigateToAddDevice(Intent intent) {
        startActivityForResult(intent, BaseActivity.REQUEST_ADD_DEVICE);
    }

    @Override
    public void navigateToEditDevice(Intent intent) {
        startActivityForResult(intent, BaseActivity.REQUEST_EDIT_DEVICE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.activityResult(requestCode, resultCode, data);
    }

    @Override
    public void addItemDeviceRecycler(DeviceData deviceData) {
        ((DeviceRecyclerAdminFragment) getFragmentManager().findFragmentById(R.id.container)).addDeviceItem(deviceData);
    }

    @Override
    public void renderItemDeviceRecycler(DeviceData deviceData) {
        ((DeviceRecyclerAdminFragment) getFragmentManager().findFragmentById(R.id.container)).renderRecyclerItem(deviceData);
    }

    @Override
    public void toEditDevice(DeviceData deviceData) {
        navigateToEditDevice(presenter.createIntentUpdateDevice(this, deviceData));
    }

    @Override
    public void toDeviceLogTracking(DeviceData deviceData) {
        navigateToDeviceLog(presenter.createIntentDeviceLog(deviceData));
    }

    public void navigateToDeviceLog(Intent intent) {
        startActivityForResult(intent, BaseActivity.REQUEST_LOG_DEVICE);
    }
}
