package com.tokopedia.devicetracker.ui.interactors;

import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
public interface TrackingDataInteractor {


    void trackingBorrowDevice(DeviceData deviceId, PersonData personData);

    void trackingReturnDevice(DeviceData deviceId, PersonData personData);

    void trackingReturnDevice(DeviceData deviceId, String url);

    void trackingAddDevice(DeviceData deviceData, PersonData personData);

    void trackingRemoveDevice(DeviceData deviceData, PersonData personData);


}
