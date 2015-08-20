package com.tokopedia.devicetracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tokopedia.devicetracker.app.AppLog;
import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.model.BorrowData;
import com.tokopedia.devicetracker.database.model.BorrowerData;
import com.tokopedia.devicetracker.database.model.DeviceData;

import java.sql.SQLException;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = DbHelper.class.getSimpleName();

    private Dao<DeviceData, Integer> deviceDataIntegerDao = null;
    private Dao<BorrowData, Integer> borrowDataIntegerDao = null;
    private Dao<BorrowerData, Integer> borrowerDataIntegerDao = null;

    public DbHelper(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        AppLog.debug(TAG, "on create");
        try {
            TableUtils.createTable(connectionSource, DeviceData.class);
            TableUtils.createTable(connectionSource, BorrowData.class);
            TableUtils.createTable(connectionSource, BorrowerData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        fillData();
    }

    private void fillData() {
        for (int i = 0; i < 20; i++) {
            DeviceData deviceData = new DeviceData();
            deviceData.setDeviceName("Device " + String.valueOf(i));
            deviceData.setDeviceModel("Model " + String.valueOf(i));
            deviceData.setDevicePicAsset("picture/image1.jpg");
            deviceData.setDeviceNumber(String.valueOf(i + 1000));
            deviceData.setDeviceDesc("Description " + String.valueOf(i));
            MainApp.getInstance().getDbService().getDeviceData().saveData(deviceData);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        AppLog.debug(TAG, "on upgrade");
        try {
            TableUtils.createTableIfNotExists(connectionSource, DeviceData.class);
            TableUtils.createTableIfNotExists(connectionSource, BorrowData.class);
            TableUtils.createTableIfNotExists(connectionSource, BorrowerData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Dao<BorrowerData, Integer> getBorrowerDataIntegerDao() throws SQLException {
        if (borrowerDataIntegerDao == null) {
            borrowerDataIntegerDao = getDao(BorrowerData.class);
        }
        return borrowerDataIntegerDao;
    }

    public Dao<BorrowData, Integer> getBorrowDataIntegerDao() throws SQLException {
        if (borrowDataIntegerDao == null) {
            borrowDataIntegerDao = getDao(BorrowData.class);
        }
        return borrowDataIntegerDao;
    }

    public Dao<DeviceData, Integer> getDeviceDataIntegerDao() throws SQLException {
        if (deviceDataIntegerDao == null) {
            deviceDataIntegerDao = getDao(DeviceData.class);
        }
        return deviceDataIntegerDao;
    }

    @Override
    public void close() {
        super.close();
        this.deviceDataIntegerDao = null;
        this.borrowDataIntegerDao = null;
        this.borrowerDataIntegerDao = null;
    }
}