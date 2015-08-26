package com.tokopedia.devicetracker.interactors.iteractorimpl;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.DbService;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.interactors.listener.OnTrackingDataFinishedListener;
import com.tokopedia.devicetracker.interactors.interactor.UserTrackingDataInteractor;

import javax.inject.Inject;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
public class UserTrackingDataInteractorImpl implements UserTrackingDataInteractor {
    private static final String TAG = UserTrackingDataInteractorImpl.class.getSimpleName();
    private OnTrackingDataFinishedListener<TrackingData, String> listener;

    public UserTrackingDataInteractorImpl(OnTrackingDataFinishedListener<TrackingData, String> listener) {
        this.listener = listener;

    }

    @Override
    public void trackingBorrowDevice(DeviceData deviceData, PersonData personData) {
        deviceData.setBorrowed(true);
        MainApp.getInstance().getDbService().getDeviceData().saveData(deviceData);
        MainApp.getInstance().getDbService().getPersonData().saveData(personData);
        TrackingData trackingData = new TrackingData();
        trackingData.setDevice(deviceData);
        trackingData.setPerson(personData);
        trackingData.setActivity(TrackingData.ACTIVITY_BORROW);
        if (MainApp.getInstance().getDbService().getTrackingData().saveData(trackingData)) {
            listener.onDataTracked(trackingData);
        } else {
            listener.onFailDataTracking("Kesalahan Database, Ulangi lagi!");
        }
    }

    @Override
    public void trackingReturnDevice(DeviceData deviceData, PersonData personData) {
        deviceData.setBorrowed(false);
        MainApp.getInstance().getDbService().getDeviceData().saveData(deviceData);
        MainApp.getInstance().getDbService().getPersonData().saveData(personData);
        TrackingData trackingData = new TrackingData();
        trackingData.setDevice(deviceData);
        trackingData.setPerson(personData);
        trackingData.setActivity(TrackingData.ACTIVITY_RETURN);
        if (MainApp.getInstance().getDbService().getTrackingData().saveData(trackingData)) {
            listener.onDataTracked(trackingData);
        } else {
            listener.onFailDataTracking("Kesalahan Database, Ulangi lagi!");
        }
    }

    @Override
    public void trackingReturnDevice(DeviceData deviceId, String url) {
        trackingValidatePersonOnDevice(deviceId, url);
    }

    @Override
    public TrackingData getLastTrackingDataBydevice(DeviceData deviceData) {
        return MainApp.getInstance().getDbService().getTrackingData().getLastBorrowDataByDevice(deviceData);
    }

    public void trackingValidatePersonOnDevice(DeviceData deviceData, String url) {
        TrackingData trackingData = MainApp.getInstance().getDbService().getTrackingData().getLastBorrowDataByDevice(deviceData);
        if (trackingData.getPerson().getUrl().equals(url)) {
            trackingReturnDevice(deviceData, trackingData.getPerson());
        } else {
            listener.onFailDataTracking("Data Tidak sama dengan peminjam");
        }
    }
}
