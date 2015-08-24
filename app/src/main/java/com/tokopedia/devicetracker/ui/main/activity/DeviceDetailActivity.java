package com.tokopedia.devicetracker.ui.main.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.BaseActivity;
import com.tokopedia.devicetracker.ui.main.fragment.DeviceDetailFragment;

public class DeviceDetailActivity extends BaseActivity implements DeviceDetailFragment.OnFragmentInteractionListener {

    public static final String EXTRA_DEVICE_DATA = "EXTRA_DEVICE_DATA";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            DeviceData deviceData = extras.getParcelable(EXTRA_DEVICE_DATA);
            DeviceDetailFragment detailFragment = (DeviceDetailFragment) getFragmentManager()
                    .findFragmentById(R.id.container_detail);
            detailFragment.renderDeviceDetail(deviceData);
        }
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_device_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_device_detail, menu);
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
    public void renderListItem(DeviceData deviceData) {

    }
}
