package com.tokopedia.devicetracker.interactors.interactor;

import com.tokopedia.devicetracker.database.model.DeviceData;

/**
 * Created by Angga.Prasetiyo on 26/08/2015.
 */
public interface ListLogTrackingDataInteractor {

    void getTrackingDataListByDevice(DeviceData deviceData);

}
