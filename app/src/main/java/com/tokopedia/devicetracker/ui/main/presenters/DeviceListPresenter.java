package com.tokopedia.devicetracker.ui.main.presenters;


import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.model.DeviceData;

import java.util.List;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DeviceListPresenter extends Presenter {
    private static final String TAG = DeviceListPresenter.class.getSimpleName();
    private View view;

    public DeviceListPresenter(View view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        if (view.isReady()) {
            view.initialView();
            view.populateListView();
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public List<DeviceData> getDeviceData() {
        return MainApp.getInstance().getDbService().getDeviceData().getListAll();
    }

    public interface View {

        boolean isReady();

        boolean isAlreadyLoaded();

        void populateListView();

        void initialView();

        void onDeviceListItemClicked(DeviceData deviceData);
    }
}
