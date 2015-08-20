package com.tokopedia.devicetracker.ui.interactors;

import com.tokopedia.devicetracker.database.model.BorrowerData;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public interface OnGetEmployeeFinishedListener {

    void onSuccess(BorrowerData employeeName);

    void onError(String errorMessage);

    void onProcess();
}
