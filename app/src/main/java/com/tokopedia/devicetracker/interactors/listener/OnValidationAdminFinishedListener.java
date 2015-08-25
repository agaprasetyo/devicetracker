package com.tokopedia.devicetracker.interactors.listener;

import com.tokopedia.devicetracker.database.model.PersonData;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public interface OnValidationAdminFinishedListener {

    void onValidationValid(PersonData personData);

    void onValidationInvalid(String message);

}
