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
        String DEVICE_NUMBER = "device_number";
        String DEVICE_MODEL = "device_model";
        String DEVICE_NAME = "device_name";
        String DEVICE_DESC = "device_desc";
        String DEVICE_PIC_ASSET = "device_pic_asset";
        String BORROW_DATA = "borrow_data";
        String BORROWED = "borrowed";
    }

    interface BorrowerData {
        String TABLE_NAME = "borrower_data";
        String ID_EMPLOYEE = "id_employee";
        String URL_EMPLOYEE = "url_employee";
        String NAME = "name";
        String DIVISION = "division";
        String POSITION = "position";
    }

    interface BorrowData {
        String TABLE_NAME = "borrow_data";
        String TIME_OF_BORROW = "time_of_borrow";
        String BORROWER = "borrower";
    }
}
