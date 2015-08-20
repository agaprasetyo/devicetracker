package com.tokopedia.devicetracker.database;

import android.content.Context;

import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.service.BorrowDataService;
import com.tokopedia.devicetracker.database.service.BorrowerDataService;
import com.tokopedia.devicetracker.database.service.DeviceDataService;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class BundleService {
    private static final String TAG = BundleService.class.getSimpleName();

    private DeviceDataService deviceData;
    private BorrowDataService borrowData;
    private BorrowerDataService borrowerData;

    public BundleService(Context context) {
        this.deviceData = new DeviceDataService(context);
        this.borrowData = new BorrowDataService(context);
        this.borrowerData = new BorrowerDataService(context);
    }

    public DeviceDataService getDeviceData() {
        return deviceData;
    }

    public BorrowDataService getBorrowData() {
        return borrowData;
    }

    public BorrowerDataService getBorrowerData() {
        return borrowerData;
    }
}


