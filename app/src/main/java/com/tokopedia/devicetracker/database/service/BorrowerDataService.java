package com.tokopedia.devicetracker.database.service;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.tokopedia.devicetracker.database.model.BorrowerData;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class BorrowerDataService extends BaseService<BorrowerData> {
    private static final String TAG = BorrowerDataService.class.getSimpleName();

    private final Dao<BorrowerData, Integer> borrowerDataIntegerDao;

    public BorrowerDataService(Context context) {
        super(context);
        try {
            borrowerDataIntegerDao = dbHelper.getBorrowerDataIntegerDao();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteData(BorrowerData object) {

    }

    @Override
    public void saveData(BorrowerData object) {
        try {
            borrowerDataIntegerDao.createOrUpdate(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BorrowerData> getListAll() {
        return null;
    }

    @Override
    public BorrowerData getData(int id) {
        return null;
    }
}
