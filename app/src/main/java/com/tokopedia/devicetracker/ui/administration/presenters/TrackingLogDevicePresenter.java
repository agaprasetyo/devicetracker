package com.tokopedia.devicetracker.ui.administration.presenters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.interactors.interactor.ListLogTrackingDataInteractor;
import com.tokopedia.devicetracker.interactors.iteractorimpl.ListLogTrackingDataInteractorImpl;
import com.tokopedia.devicetracker.interactors.listener.OnTrackingDataFinishedListener;
import com.tokopedia.devicetracker.ui.administration.activity.FormDeviceActivity;
import com.tokopedia.devicetracker.ui.administration.activity.TrackingLogDeviceActivity;
import com.tokopedia.devicetracker.ui.main.presenters.Presenter;

import java.util.List;

/**
 * Created by Angga.Prasetiyo on 26/08/2015.
 */
public class TrackingLogDevicePresenter extends Presenter implements OnTrackingDataFinishedListener<List<TrackingData>, String> {
    private static final String TAG = TrackingLogDevicePresenter.class.getSimpleName();

    private View view;
    private Activity activity;
    private ListLogTrackingDataInteractor listLogTrackingDataInteractor;

    public TrackingLogDevicePresenter(Activity activity, View view) {
        this.view = view;
        this.activity = activity;
        this.listLogTrackingDataInteractor = new ListLogTrackingDataInteractorImpl(this);
    }

    @Override
    public void initialize() {
        view.setAttributeVar();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void optionsItemSelected(MenuItem item) {

    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onDataTracked(List<TrackingData> successResultObj) {
        view.populateRecycler(successResultObj);
    }

    @Override
    public void onFailDataTracking(String failedResultObj) {
        view.showToastMessage(failedResultObj);
    }

    private void grabTrackingData(DeviceData deviceData) {
        listLogTrackingDataInteractor.getTrackingDataListByDevice(deviceData);
    }

    public void initialBundleData(Bundle extras) {
        if (extras != null) {
            DeviceData deviceData = extras.getParcelable(TrackingLogDeviceActivity.EXTRA_DEVICE_DATA);
            if (deviceData != null) {
                grabTrackingData(deviceData);
            }
        }
    }

    public interface View {

        void setAttributeVar();

        void repopulateRecycler();

        void populateRecycler(List<TrackingData> successResultObj);

        void showToastMessage(String message);
    }
}
