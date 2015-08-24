package com.tokopedia.devicetracker.database.service;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.tokopedia.devicetracker.database.DbHelper;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public abstract class BaseService<T> {
    private static final String TAG = BaseService.class.getSimpleName();

    protected final DbHelper dbHelper;

    public BaseService(Context context) {
        dbHelper = OpenHelperManager.getHelper(context, DbHelper.class);
    }

    public abstract boolean deleteData(T object);

    public abstract boolean saveData(T object);

    public abstract List<T> getListAll();

    public abstract T getData(int id);
}