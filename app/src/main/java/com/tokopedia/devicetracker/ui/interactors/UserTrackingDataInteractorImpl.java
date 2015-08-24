package com.tokopedia.devicetracker.ui.interactors;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.DbService;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;

import javax.inject.Inject;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
public class UserTrackingDataInteractorImpl implements UserTrackingDataInteractor {
    private static final String TAG = UserTrackingDataInteractorImpl.class.getSimpleName();
    private OnTrackingDataFinishedListener listener;
    @Inject DbService dbService;

    public UserTrackingDataInteractorImpl(OnTrackingDataFinishedListener listener) {
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
            listener.onTracked(trackingData);
        } else {
            listener.onFailTracking("Kesalahan Database, Ulangi lagi!");
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
            listener.onTracked(trackingData);
        } else {
            listener.onFailTracking("Kesalahan Database, Ulangi lagi!");
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
            listener.onFailTracking("Data Tidak sama dengan peminjam");
        }
    }
}
