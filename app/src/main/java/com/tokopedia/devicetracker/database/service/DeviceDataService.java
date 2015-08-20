package com.tokopedia.devicetracker.database.service;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.tokopedia.devicetracker.database.model.DeviceData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DeviceDataService extends BaseService<DeviceData> {
    private static final String TAG = DeviceDataService.class.getSimpleName();

    private final Dao<DeviceData, Integer> deviceDataIntegerDao;

    public DeviceDataService(Context context) {
        super(context);
        try {
            deviceDataIntegerDao = dbHelper.getDeviceDataIntegerDao();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteData(DeviceData object) {

    }

    @Override
    public void saveData(DeviceData object) {
        try {
            deviceDataIntegerDao.createOrUpdate(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DeviceData> getListAll() {
        try {
            return deviceDataIntegerDao.queryForAll();
            //        return new ArrayList<>();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public DeviceData getData(int id) {
        return null;
    }
}
