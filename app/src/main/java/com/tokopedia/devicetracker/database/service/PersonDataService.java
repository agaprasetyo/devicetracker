package com.tokopedia.devicetracker.database.service;

import android.content.Context;

import com.tokopedia.devicetracker.database.model.PersonData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
public class PersonDataService extends BaseService<PersonData> {
    private static final String TAG = PersonDataService.class.getSimpleName();

    public PersonDataService(Context context) {
        super(context);
    }

    @Override
    public boolean deleteData(PersonData object) {
        try {
            personDataIntegerDao.delete(object);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveData(PersonData object) {
        try {
            personDataIntegerDao.createOrUpdate(object);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PersonData> getListAll() {
        try {
            return personDataIntegerDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public PersonData getData(int id) {
        try {
            return personDataIntegerDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
