package com.tokopedia.devicetracker.interactors.listener;

import com.tokopedia.devicetracker.database.model.TrackingData;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
public interface OnTrackingDataFinishedListener {

    void onDataTracked(TrackingData trackingData);

    void onFailDataTracking(String failMessage);
}
