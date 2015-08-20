package com.tokopedia.devicetracker.ui.main.presenters;

import android.app.FragmentManager;
import android.content.Intent;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.main.activity.DeviceDetailActivity;
import com.tokopedia.devicetracker.ui.main.activity.MainActivity;
import com.tokopedia.devicetracker.ui.main.fragment.DeviceDetailFragment;

/**
 * Created by Angga.Prasetiyo on 19/08/2015.
 */
public class MainActivityPresenter extends Presenter {
    private static final String TAG = MainActivityPresenter.class.getSimpleName();
    private View view;

    public MainActivityPresenter(View view) {
        this.view = view;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void processRenderDeviceData(MainActivity mainActivity, FragmentManager fragmentManager, DeviceData deviceData) {
        DeviceDetailFragment fragment = (DeviceDetailFragment) fragmentManager.findFragmentById(R.id.container_detail);
        if (fragment != null && fragment.isInLayout()) {
            ((DeviceDetailFragment) fragmentManager.findFragmentById(R.id.container_detail)).refreshDeviceStatus(deviceData);
        } else {
            Intent intent = new Intent(mainActivity,
                    DeviceDetailActivity.class);
            intent.putExtra(DeviceDetailActivity.EXTRA_DEVICE_DATA, deviceData);
            mainActivity.startActivity(intent);
        }
    }

    public interface View {

    }
}
