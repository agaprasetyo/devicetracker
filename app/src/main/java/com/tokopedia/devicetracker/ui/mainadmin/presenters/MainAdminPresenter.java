package com.tokopedia.devicetracker.ui.mainadmin.presenters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.baseui.BaseActivity;
import com.tokopedia.devicetracker.ui.mainadmin.activity.FormDeviceActivity;
import com.tokopedia.devicetracker.ui.mainadmin.activity.TrackingLogDeviceActivity;
import com.tokopedia.devicetracker.ui.mainadmin.fragment.DeviceRecyclerAdminFragment;
import com.tokopedia.devicetracker.ui.presenter.Presenter;

/**
 * Created by Angga.Prasetiyo on 26/08/2015.
 */
public class MainAdminPresenter extends Presenter {
    private static final String TAG = MainAdminPresenter.class.getSimpleName();

    private View view;
    private Activity activity;

    public MainAdminPresenter(Activity activity, View view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void initialize() {
        view.setAttributeVar();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void optionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_device:
                view.navigateToAddDevice(createIntentAddDevice());
                break;
        }
    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case BaseActivity.REQUEST_ADD_DEVICE:
                    Bundle bundleAddDevice = data.getExtras();
                    if (bundleAddDevice != null) {
                        DeviceData deviceData = bundleAddDevice.getParcelable(FormDeviceActivity.EXTRA_DEVICE_DATA);
                        view.addItemDeviceRecycler(deviceData);
                    }
                    break;

                case BaseActivity.REQUEST_EDIT_DEVICE:
                    Bundle bundleEditData = data.getExtras();
                    if (bundleEditData != null) {
                        DeviceData deviceData = bundleEditData.getParcelable(FormDeviceActivity.EXTRA_DEVICE_DATA);
                        view.renderItemDeviceRecycler(deviceData);
                    }
                    break;
            }
        }
    }

    public Intent createIntentUpdateDevice(Activity activity, DeviceData deviceData) {
        return FormDeviceActivity.factoryIntentUpdate(activity, deviceData);
    }

    public Intent createIntentAddDevice() {
        return FormDeviceActivity.factoryIntentAdd(activity);
    }

    private DeviceRecyclerAdminFragment getDeviceRecyclerFragment(FragmentManager fragmentManager) {

        return (DeviceRecyclerAdminFragment) activity.getFragmentManager().findFragmentById(R.id.container);
    }

    public Intent createIntentDeviceLog(DeviceData deviceData) {
        return TrackingLogDeviceActivity.factoryIntent(activity, deviceData);
    }


    public interface View {

        void setAttributeVar();

        void navigateToAddDevice(Intent intent);

        void navigateToEditDevice(Intent intent);

        void navigateToDeviceLog(Intent intent);

        void addItemDeviceRecycler(DeviceData deviceData);

        void renderItemDeviceRecycler(DeviceData deviceData);

    }
}
