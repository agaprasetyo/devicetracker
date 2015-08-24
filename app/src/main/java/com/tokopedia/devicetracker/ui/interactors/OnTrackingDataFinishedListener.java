package com.tokopedia.devicetracker.ui.interactors;

import com.tokopedia.devicetracker.database.model.TrackingData;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
public interface OnTrackingDataFinishedListener {

    void onTracked(TrackingData trackingData);

    void onFailTracking(TrackingData trackingData);
}
