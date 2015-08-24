package com.tokopedia.devicetracker.database;

import android.content.Context;

import com.tokopedia.devicetracker.database.service.DeviceDataService;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DbService {
    private static final String TAG = DbService.class.getSimpleName();

    private DeviceDataService deviceData;


    public DbService(Context context) {
        this.deviceData = new DeviceDataService(context);

    }

    public DeviceDataService getDeviceData() {
        return deviceData;
    }


}


