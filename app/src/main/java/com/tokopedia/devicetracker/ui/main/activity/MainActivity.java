package com.tokopedia.devicetracker.ui.main.activity;

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
    protected int getResourceLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateDetailFragment(DeviceData deviceData) {
        DeviceDetailFragment fragment = (DeviceDetailFragment) fragmentManager.findFragmentById(R.id.container_detail);
        if (fragment != null && fragment.isInLayout()) {
            presenter.sendDeviceDataToDetailFragment(fragmentManager, deviceData);
        } else {
            presenter.sendDeviceDataToDetailActivity(this, deviceData);
        }
    }
}
