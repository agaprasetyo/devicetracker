package com.tokopedia.devicetracker.database.service;

import android.content.Context;

import com.tokopedia.devicetracker.database.DbContract;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
public class TrackingDataService extends BaseService<TrackingData> {
    private static final String TAG = TrackingDataService.class.getSimpleName();


    public TrackingDataService(Context context) {
        super(context);
    }

    @Override
    public boolean deleteData(TrackingData object) {
        try {
            trackingDataIntegerDao.delete(object);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveData(TrackingData object) {
        try {
            trackingDataIntegerDao.createOrUpdate(object);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<TrackingData> getListAll() {
        try {
            return trackingDataIntegerDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public TrackingData getData(int id) {
        try {
            return trackingDataIntegerDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public TrackingData getLastBorrowDataByDevice(DeviceData deviceData) {
        try {
            return trackingDataIntegerDao.queryBuilder()
                    .limit((long) 1)
                    .orderBy(DbContract.ID, false)
                    .where()
                    .eq(DbContract.TrackingData.DEVICE, deviceData)
                    .and()
                    .eq(DbContract.TrackingData.ACTIVITY, TrackingData.ACTIVITY_BORROW)
                    .queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
