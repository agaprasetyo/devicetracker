package com.tokopedia.devicetracker.ui.mainuser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.baseui.BaseActivity;
import com.tokopedia.devicetracker.ui.mainuser.fragment.DeviceDetailFragment;
import com.tokopedia.devicetracker.ui.mainuser.fragment.DeviceRecyclerUserFragment;
import com.tokopedia.devicetracker.ui.mainuser.presenters.MainUserPresenter;

public class MainUserActivity extends BaseActivity<MainUserPresenter> implements DeviceRecyclerUserFragment.OnFragmentInteractionListener,
        DeviceDetailFragment.OnFragmentInteractionListener,
        MainUserPresenter.View {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initialize();
    }

    @Override
    protected void initialPresenter() {
        presenter = new MainUserPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.optionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.activityResult(requestCode, resultCode, data);
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
        ((DeviceRecyclerUserFragment) getFragmentManager().findFragmentById(R.id.container)).renderRecyclerItem(deviceData);
    }

    @Override
    public void navigateToAdministration() {
        startActivityForResult(presenter.createIntentAdministration(this), BaseActivity.REQUEST_ADMIN_OPERATION);
    }

    @Override
    public void setAttributeVar() {

    }

    @Override
    public void repopulateRecyclerDevices() {
        ((DeviceRecyclerUserFragment) getFragmentManager().findFragmentById(R.id.container)).populateListView();
    }
}
