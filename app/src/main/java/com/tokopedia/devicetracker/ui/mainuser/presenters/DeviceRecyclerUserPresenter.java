package com.tokopedia.devicetracker.ui.mainuser.presenters;


import android.content.Intent;
import android.view.MenuItem;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.presenter.Presenter;

import java.util.List;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DeviceRecyclerUserPresenter extends Presenter {
    private static final String TAG = DeviceRecyclerUserPresenter.class.getSimpleName();
    private View view;

    public DeviceRecyclerUserPresenter(View view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        if (view.isReady()) {
            view.setAttributeVar();
            view.populateListView();
        }
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

    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {

    }

    public List<DeviceData> getDeviceData() {
        return MainApp.getInstance().getDbService().getDeviceData().getListAll();
    }

    public interface View {

        boolean isReady();

        boolean isAlreadyLoaded();

        void populateListView();

        void setAttributeVar();

        void renderRecyclerItem(DeviceData deviceId);

    }
}
