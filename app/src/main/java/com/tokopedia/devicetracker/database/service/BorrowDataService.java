package com.tokopedia.devicetracker.database.service;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.tokopedia.devicetracker.database.DbContract;
import com.tokopedia.devicetracker.database.model.BorrowData;
import com.tokopedia.devicetracker.database.model.BorrowerData;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class BorrowDataService extends BaseService<BorrowData> {
    private static final String TAG = BorrowDataService.class.getSimpleName();

    private final Dao<BorrowData, Integer> borrowDataIntegerDao;

    public BorrowDataService(Context context) {
        super(context);
        try {
            borrowDataIntegerDao = dbHelper.getBorrowDataIntegerDao();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteData(BorrowData object) {

    }

    @Override
    public void saveData(BorrowData object) {
        try {
            borrowDataIntegerDao.createOrUpdate(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BorrowData> getListAll() {
        return null;
    }

    @Override
    public BorrowData getData(int id) {
        return null;
    }
}
