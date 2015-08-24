package com.tokopedia.devicetracker.database;

import com.tokopedia.devicetracker.BuildConfig;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public interface DbContract {

    String DATABASE_NAME = "database.db";
    int DATABASE_VERSION = BuildConfig.VERSION_CODE;
    String ID = "_id";

    interface DeviceData {
        String TABLE_NAME = "device_data";
        String DEVICE_MODEL = "device_model";
        String DEVICE_NAME = "device_name";
        String DEVICE_DESC = "device_desc";
        String DEVICE_PIC_ASSET = "device_pic_path";
        String BORROWED = "borrowed";
        String N_STATUS = "N_STATUS";
    }

    interface PersonData {
        String TABLE_NAME = "person_data";
        String NAME = "name";
        String URL = "url";
    }

    interface TrackingData {
        String TABLE_NAME = "tracking_data";
        String DEVICE = "device_data";
        String TIME = "time";
        String ACTIVITY = "activity";
        String PERSON = "person";
    }


}
