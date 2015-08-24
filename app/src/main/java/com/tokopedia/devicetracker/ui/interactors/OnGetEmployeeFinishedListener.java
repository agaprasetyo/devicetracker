package com.tokopedia.devicetracker.ui.interactors;

import com.tokopedia.devicetracker.database.model.PersonData;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public interface OnGetEmployeeFinishedListener {

    void onSuccess(PersonData personData);

    void onError(String errorMessage);

    void onProcess();
}
