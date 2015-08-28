package com.tokopedia.devicetracker.ui.mainuser.presenters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.MenuItem;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.baseui.BaseActivity;
import com.tokopedia.devicetracker.ui.mainadmin.activity.MainAdminActivity;
import com.tokopedia.devicetracker.ui.mainuser.activity.DeviceDetailActivity;
import com.tokopedia.devicetracker.ui.mainuser.activity.MainUserActivity;
import com.tokopedia.devicetracker.ui.mainuser.fragment.DeviceDetailFragment;
import com.tokopedia.devicetracker.ui.presenter.Presenter;

/**
 * Created by Angga.Prasetiyo on 19/08/2015.
 */
public class MainUserPresenter extends Presenter {
    private static final String TAG = MainUserPresenter.class.getSimpleName();
    private View view;

    public MainUserPresenter(View view) {
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
        if (item.getItemId() == R.id.action_settings) {
            view.navigateToAdministration();
        }
    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BaseActivity.REQUEST_ADMIN_OPERATION) view.repopulateRecyclerDevices();
    }

    public void processRenderDeviceData(MainUserActivity mainUserActivity, FragmentManager fragmentManager, DeviceData deviceData) {
        DeviceDetailFragment fragment = (DeviceDetailFragment) fragmentManager.findFragmentById(R.id.container_detail);
        if (fragment != null && fragment.isInLayout()) {
            fragment.renderDeviceDetail(deviceData);
        } else {
            Intent intent = new Intent(mainUserActivity,
                    DeviceDetailActivity.class);
            intent.putExtra(DeviceDetailActivity.EXTRA_DEVICE_DATA, deviceData);
            mainUserActivity.startActivity(intent);
        }
    }

    public Intent createIntentAdministration(Activity activity) {
        return MainAdminActivity.factoryIntent(activity);
    }

    public interface View {

        void navigateToAdministration();

        void setAttributeVar();

        void repopulateRecyclerDevices();

    }
}
