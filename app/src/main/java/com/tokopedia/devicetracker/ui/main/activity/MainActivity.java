package com.tokopedia.devicetracker.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            navigateToAdministration();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void renderDetailDeviceData(DeviceData deviceData) {
        presenter.processRenderDeviceData(this, getFragmentManager(), deviceData);
    }

    @Override
    public void renderListItem(DeviceData deviceData) {
        ((DeviceListFragment) getFragmentManager().findFragmentById(R.id.container)).renderItemList(deviceData);
    }

    @Override
    public void navigateToAdministration() {
        startActivity(presenter.createIntentAdministration(this));
    }

    @Override
    public void setAttributeVar() {

    }
}
