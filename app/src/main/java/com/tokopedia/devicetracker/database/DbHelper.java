package com.tokopedia.devicetracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tokopedia.devicetracker.app.AppLog;
import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;

import java.sql.SQLException;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = DbHelper.class.getSimpleName();

    private Dao<DeviceData, Integer> deviceDataIntegerDao = null;
    private Dao<TrackingData, Integer> trackingDataIntegerDao = null;
    private Dao<PersonData, Integer> personDataIntegerDao = null;

    public DbHelper(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        AppLog.debug(TAG, "on create");
        try {
            TableUtils.createTable(connectionSource, DeviceData.class);
            TableUtils.createTable(connectionSource, TrackingData.class);
            TableUtils.createTable(connectionSource, PersonData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        fillData();
    }

    private void fillData() {
        for (int i = 0; i < 20; i++) {
            DeviceData deviceData = new DeviceData();
            deviceData.setId(i + 1);
            deviceData.setDeviceName("Device " + String.valueOf(i + 1));
            deviceData.setDeviceModel("Model " + String.valueOf(i + 1));
            deviceData.setDevicePicPath("picture/image1.jpg");
            deviceData.setDeviceDesc("Description " + String.valueOf(i + 1));
            MainApp.getInstance().getDbService().getDeviceData().saveData(deviceData);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        AppLog.debug(TAG, "on upgrade");
        try {
            TableUtils.createTableIfNotExists(connectionSource, DeviceData.class);
            TableUtils.createTableIfNotExists(connectionSource, PersonData.class);
            TableUtils.createTableIfNotExists(connectionSource, TrackingData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Dao<DeviceData, Integer> getDeviceDataIntegerDao() throws SQLException {
        if (deviceDataIntegerDao == null) {
            deviceDataIntegerDao = getDao(DeviceData.class);
        }
        return deviceDataIntegerDao;
    }

    public Dao<PersonData, Integer> getPersonDataIntegerDao() throws SQLException {
        if (personDataIntegerDao == null) {
            personDataIntegerDao = getDao(PersonData.class);
        }
        return personDataIntegerDao;
    }

    public Dao<TrackingData, Integer> getTrackingDataIntegerDao() throws SQLException {
        if (trackingDataIntegerDao == null) {
            trackingDataIntegerDao = getDao(TrackingData.class);
        }
        return trackingDataIntegerDao;
    }

    @Override
    public void close() {
        super.close();
        this.deviceDataIntegerDao = null;
        this.trackingDataIntegerDao = null;
        this.personDataIntegerDao = null;
    }
}