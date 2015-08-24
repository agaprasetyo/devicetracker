package com.tokopedia.devicetracker.database;

import android.content.Context;

import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.service.DeviceDataService;
import com.tokopedia.devicetracker.database.service.PersonDataService;
import com.tokopedia.devicetracker.database.service.TrackingDataService;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DbService {
    private static final String TAG = DbService.class.getSimpleName();

    private DeviceDataService deviceData;
    private PersonDataService personData;
    private TrackingDataService trackingData;


    public DbService(Context context) {
        this.deviceData = new DeviceDataService(context);
        this.personData = new PersonDataService(context);
        this.trackingData = new TrackingDataService(context);
    }

    public DeviceDataService getDeviceData() {
        return deviceData;
    }

    public PersonDataService getPersonData() {
        return personData;
    }

    public TrackingDataService getTrackingData() {
        return trackingData;
    }
}


