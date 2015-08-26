package com.tokopedia.devicetracker.interactors.iteractorimpl;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.interactors.interactor.ListLogTrackingDataInteractor;
import com.tokopedia.devicetracker.interactors.listener.OnTrackingDataFinishedListener;

import java.util.List;

/**
 * Created by Angga.Prasetiyo on 26/08/2015.
 */
public class ListLogTrackingDataInteractorImpl implements ListLogTrackingDataInteractor {
    private static final String TAG = ListLogTrackingDataInteractorImpl.class.getSimpleName();

    private OnTrackingDataFinishedListener<List<TrackingData>, String> listener;

    public ListLogTrackingDataInteractorImpl(OnTrackingDataFinishedListener<List<TrackingData>, String> listener) {
        this.listener = listener;
    }

    @Override
    public void getTrackingDataListByDevice(DeviceData deviceData) {
        List<TrackingData> trackingDatas = MainApp.getInstance().getDbService().getTrackingData().getListByDevice(deviceData);
        if (!trackingDatas.isEmpty()) {
            listener.onDataTracked(trackingDatas);
        } else {
            listener.onFailDataTracking("Data Kosong!");
        }
    }
}
