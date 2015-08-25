package com.tokopedia.devicetracker.interactors.interactor;

import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
public interface UserTrackingDataInteractor {

    void trackingBorrowDevice(DeviceData deviceId, PersonData personData);

    void trackingReturnDevice(DeviceData deviceData, PersonData personData);

    void trackingReturnDevice(DeviceData deviceData, String urlPerson);

    TrackingData getLastTrackingDataBydevice(DeviceData deviceData);
}
