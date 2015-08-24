package com.tokopedia.devicetracker.ui.interactors;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.DbContract;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.ui.main.presenters.DeviceDetailPresenter;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
public class TrackingDataInteractorImpl implements TrackingDataInteractor {
    private static final String TAG = TrackingDataInteractorImpl.class.getSimpleName();
    private OnTrackingDataFinishedListener listener;

    public TrackingDataInteractorImpl(OnTrackingDataFinishedListener listener) {
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
            listener.onFailTracking(trackingData);
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
            listener.onFailTracking(trackingData);
        }
    }

    @Override
    public void trackingReturnDevice(DeviceData deviceId, String url) {
        trackingValidatePersonOnDevice(deviceId, url);
    }

    @Override
    public void trackingAddDevice(DeviceData deviceData, PersonData personData) {
        deviceData.setnStatus(DeviceData.STATUS_ACTIVE);
        TrackingData trackingData = new TrackingData();
        trackingData.setDevice(deviceData);
        trackingData.setPerson(personData);
        trackingData.setActivity(TrackingData.ACTIVITY_ADD_DEVICE);
        if (MainApp.getInstance().getDbService().getTrackingData().saveData(trackingData)) {
            listener.onTracked(trackingData);
        } else {
            listener.onFailTracking(trackingData);
        }
    }

    @Override
    public void trackingRemoveDevice(DeviceData deviceData, PersonData personData) {
        deviceData.setnStatus(DeviceData.STATUS_DELETED);
        TrackingData trackingData = new TrackingData();
        trackingData.setDevice(deviceData);
        trackingData.setPerson(personData);
        trackingData.setActivity(TrackingData.ACTIVITY_REMOVE_DEVICE);
        if (MainApp.getInstance().getDbService().getTrackingData().saveData(trackingData)) {
            listener.onTracked(trackingData);
        } else {
            listener.onFailTracking(trackingData);
        }
    }

    public void trackingValidatePersonOnDevice(DeviceData deviceData, String url) {
        TrackingData trackingData = MainApp.getInstance().getDbService().getTrackingData().getLastBorrowDataByDevice(deviceData);
        if (trackingData.getPerson().getUrl().equals(url)) {
            trackingReturnDevice(deviceData, trackingData.getPerson());
        } else {
            listener.onFailTracking(trackingData);
        }
    }
}
