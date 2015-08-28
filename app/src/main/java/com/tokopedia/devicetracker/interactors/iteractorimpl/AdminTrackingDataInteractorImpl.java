package com.tokopedia.devicetracker.interactors.iteractorimpl;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.interactors.interactor.AdminTrackingDataInteractor;
import com.tokopedia.devicetracker.interactors.listener.OnTrackingDataFinishedListener;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public class AdminTrackingDataInteractorImpl implements AdminTrackingDataInteractor {
    private static final String TAG = AdminTrackingDataInteractorImpl.class.getSimpleName();

    private OnTrackingDataFinishedListener<TrackingData, String> listener;

    public AdminTrackingDataInteractorImpl(OnTrackingDataFinishedListener<TrackingData, String> listener) {
        this.listener = listener;
    }

    @Override
    public void trackingDataAddDevice(DeviceData deviceData, PersonData personData) {
        deviceData.setnStatus(DeviceData.STATUS_ACTIVE);
        MainApp.getInstance().getDbService().getDeviceData().saveData(deviceData);
        MainApp.getInstance().getDbService().getPersonData().saveData(personData);
        TrackingData trackingData = new TrackingData();
        trackingData.setDevice(deviceData);
        trackingData.setPerson(personData);
        trackingData.setActivity(TrackingData.ACTIVITY_ADD_DEVICE);
        if (MainApp.getInstance().getDbService().getTrackingData().saveData(trackingData)) {
            listener.onDataTracked(trackingData);
        } else {
            listener.onFailDataTracking("Kesalahan Database, Ulangi lagi!");
        }
    }

    @Override
    public void trackingDataUpdateDevice(DeviceData deviceData, PersonData personData) {
        deviceData.setnStatus(DeviceData.STATUS_ACTIVE);
        MainApp.getInstance().getDbService().getDeviceData().saveData(deviceData);
        MainApp.getInstance().getDbService().getPersonData().saveData(personData);
        TrackingData trackingData = new TrackingData();
        trackingData.setDevice(deviceData);
        trackingData.setPerson(personData);
        trackingData.setActivity(TrackingData.ACTIVITY_UPDATE_DEVICE);
        if (MainApp.getInstance().getDbService().getTrackingData().saveData(trackingData)) {
            listener.onDataTracked(trackingData);
        } else {
            listener.onFailDataTracking("Kesalahan Database, Ulangi lagi!");
        }
    }

    @Override
    public void trackingDataDeleteDevice(DeviceData deviceData, PersonData personData) {
        deviceData.setnStatus(DeviceData.STATUS_DELETED);
        MainApp.getInstance().getDbService().getDeviceData().saveData(deviceData);
        MainApp.getInstance().getDbService().getPersonData().saveData(personData);
        TrackingData trackingData = new TrackingData();
        trackingData.setDevice(deviceData);
        trackingData.setPerson(personData);
        trackingData.setActivity(TrackingData.ACTIVITY_REMOVE_DEVICE);
        if (MainApp.getInstance().getDbService().getTrackingData().saveData(trackingData)) {
            listener.onDataTracked(trackingData);
        } else {
            listener.onFailDataTracking("Kesalahan Database, Ulangi lagi!");
        }
    }

    @Override
    public void trackingDataRestoreDevice(DeviceData deviceData, PersonData personData) {
        deviceData.setnStatus(DeviceData.STATUS_ACTIVE);
        MainApp.getInstance().getDbService().getDeviceData().saveData(deviceData);
        MainApp.getInstance().getDbService().getPersonData().saveData(personData);
        TrackingData trackingData = new TrackingData();
        trackingData.setDevice(deviceData);
        trackingData.setPerson(personData);
        trackingData.setActivity(TrackingData.ACTIVITY_RESTORE_DEVICE);
        if (MainApp.getInstance().getDbService().getTrackingData().saveData(trackingData)) {
            listener.onDataTracked(trackingData);
        } else {
            listener.onFailDataTracking("Kesalahan Database, Ulangi lagi!");
        }
    }
}
