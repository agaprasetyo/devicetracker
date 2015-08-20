package com.tokopedia.devicetracker.ui.main.activity;

import android.os.Bundle;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.BaseActivity;
import com.tokopedia.devicetracker.ui.main.fragment.DeviceDetailFragment;
import com.tokopedia.devicetracker.ui.main.fragment.DeviceListFragment;
import com.tokopedia.devicetracker.ui.main.presenters.MainActivityPresenter;

public class MainActivity extends BaseActivity implements DeviceListFragment.OnFragmentInteractionListener,
        DeviceDetailFragment.OnFragmentInteractionListener,
        MainActivityPresenter.View {

    private MainActivityPresenter presenter = new MainActivityPresenter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initialize();
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void renderDetailDeviceData(DeviceData deviceData) {
        presenter.processRenderDeviceData(this, fragmentManager, deviceData);
    }

    @Override
    public void renderListItem(DeviceData deviceId) {
        ((DeviceListFragment) fragmentManager.findFragmentById(R.id.container)).renderItemList(deviceId);
    }
}
