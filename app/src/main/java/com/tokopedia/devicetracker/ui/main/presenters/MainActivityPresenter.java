package com.tokopedia.devicetracker.ui.main.presenters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
        view.setAttributeVar();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void processRenderDeviceData(MainActivity mainActivity, FragmentManager fragmentManager, DeviceData deviceData) {
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_detail);


        if (fragment.isInLayout()) {
            if (fragment instanceof DeviceDetailFragment) {
                ((DeviceDetailFragment) fragment).renderDeviceDetail(deviceData);
            } else {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.container_detail, DeviceDetailFragment.newInstance(deviceData));
                fragmentTransaction.commit();
            }
        } else {
            Intent intent = new Intent(mainActivity,
                    DeviceDetailActivity.class);
            intent.putExtra(DeviceDetailActivity.EXTRA_DEVICE_DATA, deviceData);
            mainActivity.startActivity(intent);
        }
    }

    public interface View {

        void setAttributeVar();
    }
}
