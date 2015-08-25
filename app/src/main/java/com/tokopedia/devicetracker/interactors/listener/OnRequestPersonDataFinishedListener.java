package com.tokopedia.devicetracker.interactors.listener;

import com.tokopedia.devicetracker.database.model.PersonData;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public interface OnRequestPersonDataFinishedListener {

    void onPersonDataSuccess(PersonData personData);

    void onPersonDataError(String errorMessage);

    void onPersonDataProcess();
}
