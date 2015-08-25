package com.tokopedia.devicetracker.interactors.interactor;

import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public interface AdminTrackingDataInteractor {

    void trackingDataAddDevice(DeviceData deviceData, PersonData personData);

    void trackingDataUpdateDevice(DeviceData deviceData, PersonData personData);

    void trackingDataDeleteDevice(DeviceData deviceData, PersonData personData);

    void trackingDataRestoreDevice(DeviceData deviceData, PersonData personData);
}
