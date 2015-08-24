package com.tokopedia.devicetracker.database.service;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;

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
    public boolean deleteData(DeviceData object) {
        try {
            deviceDataIntegerDao.delete(object);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveData(DeviceData object) {
        try {
            deviceDataIntegerDao.createOrUpdate(object);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
        try {
            return deviceDataIntegerDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
