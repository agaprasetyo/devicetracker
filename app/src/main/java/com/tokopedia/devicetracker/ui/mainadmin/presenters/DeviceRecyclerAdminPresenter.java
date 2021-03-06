package com.tokopedia.devicetracker.ui.mainadmin.presenters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.MenuItem;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.interactors.interactor.AdminTrackingDataInteractor;
import com.tokopedia.devicetracker.interactors.iteractorimpl.AdminTrackingDataInteractorImpl;
import com.tokopedia.devicetracker.interactors.listener.OnTrackingDataFinishedListener;
import com.tokopedia.devicetracker.ui.mainadmin.activity.FormDeviceActivity;
import com.tokopedia.devicetracker.ui.dialog.QRScannerValidatorAdminDialog;
import com.tokopedia.devicetracker.ui.presenter.Presenter;

import java.util.List;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public class DeviceRecyclerAdminPresenter extends Presenter implements OnTrackingDataFinishedListener<TrackingData, String> {
    private static final String TAG = DeviceRecyclerAdminPresenter.class.getSimpleName();

    private Activity activity;
    private View view;
    private AdminTrackingDataInteractor adminTrackingDataInteractor;

    public DeviceRecyclerAdminPresenter(Activity activity, View view) {
        this.activity = activity;
        this.view = view;
        this.adminTrackingDataInteractor = new AdminTrackingDataInteractorImpl(this);
    }

    @Override
    public void initialize() {
        view.setAttributeVar();
        view.populateRecyclerView(MainApp.getInstance().getDbService().getDeviceData().getListAll());
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
    public void onDataTracked(TrackingData successResultObj) {
        view.removeRecyclerItem(successResultObj.getDevice());
    }

    @Override
    public void onFailDataTracking(String failedResultObj) {
        view.showToastMessage(failedResultObj);
    }

    public void deleteDevice(final DeviceData deviceData) {
        view.openQRScannerDialog(new QRScannerValidatorAdminDialog(activity, new QRScannerValidatorAdminDialog.Callback() {
            @Override
            public void onSuccessValidation(PersonData personData) {
                adminTrackingDataInteractor.trackingDataDeleteDevice(deviceData, personData);
            }

            @Override
            public void onErrorValidation(String message) {
                view.showToastMessage("Gagal Validasi");
            }
        }));
    }

    public Intent createIntentUpdateDevice(Activity activity, DeviceData deviceData) {
        return FormDeviceActivity.factoryIntentUpdate(activity, deviceData);
    }

    public Intent createIntentAddDevice() {
        return FormDeviceActivity.factoryIntentAdd(activity);
    }

    public interface View {

        void setAttributeVar();

        void populateRecyclerView(List<DeviceData> deviceDatas);

        void openQRScannerDialog(Dialog dialog);

        void showToastMessage(String message);

        void renderRecyclerItem(DeviceData device);

        void removeRecyclerItem(DeviceData device);

        void addDeviceItem(DeviceData deviceData);
    }
}
