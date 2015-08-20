package com.tokopedia.devicetracker.database.service;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.tokopedia.devicetracker.database.DbHelper;

import java.util.List;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public abstract class BaseService<T> {
    private static final String TAG = BaseService.class.getSimpleName();

    protected final Context context;
    protected final DbHelper dbHelper;

    public BaseService(Context context) {
        this.context = context;
        this.dbHelper = OpenHelperManager.getHelper(context, DbHelper.class);
    }

    public abstract void deleteData(T object);

    public abstract void saveData(T object);

    public abstract List<T> getListAll();

    public abstract T getData(int id);
}