package com.tokopedia.devicetracker.ui.main.presenters;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.DbContract;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.ui.interactors.OnGetEmployeeFinishedListener;
import com.tokopedia.devicetracker.ui.interactors.OnTrackingDataFinishedListener;
import com.tokopedia.devicetracker.ui.interactors.QRCodeInteractor;
import com.tokopedia.devicetracker.ui.interactors.QRCodeInteractorImpl;
import com.tokopedia.devicetracker.ui.interactors.TrackingDataInteractor;
import com.tokopedia.devicetracker.ui.interactors.TrackingDataInteractorImpl;
import com.tokopedia.devicetracker.ui.main.activity.MainActivity;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DeviceDetailPresenter extends Presenter implements OnGetEmployeeFinishedListener, OnTrackingDataFinishedListener {
    private static final String TAG = DeviceDetailPresenter.class.getSimpleName();

    private View view;
    private QRCodeInteractor qrCodeInteractor;
    private TrackingDataInteractor trackingDataInteractor;

    private String urlEmployee;
    private PersonData personData;

    public DeviceDetailPresenter(View view) {
        this.view = view;
        this.qrCodeInteractor = new QRCodeInteractorImpl(this);
        this.trackingDataInteractor = new TrackingDataInteractorImpl(this);
    }

    @Override
    public void initialize() {
        view.setAttributeVar();
    }

    @Override
    public void resume() {
        view.startQRCodeScanner();
    }

    @Override
    public void pause() {
        view.stopQRCodeScanner();
    }

    @Override
    public void onSuccess(PersonData personData) {
        this.personData = personData;
        view.startQRCodeScanner();
        view.showSuccessResult(personData.getName());
        view.hideProgressLayout();
    }

    @Override
    public void onError(String errorMessage) {
        view.startQRCodeScanner();
        view.hideProgressLayout();
        view.showErrorResult(errorMessage);
    }

    @Override
    public void onProcess() {
        view.showProgressLayout();
    }

    public void analyzeDeviceData(DeviceData deviceData) {
        view.resetContentView();
        this.urlEmployee = null;
        if (deviceData.isBorrowed()) {
            TrackingData trackingData = MainApp.getInstance().getDbService().getTrackingData().getLastBorrowDataByDevice(deviceData);
            view.deviceIsBorrowed(trackingData);
        } else {
            view.deviceIsAvailable();
        }

    }

    public void borrowDevice(DeviceData deviceData) {
        if (personData != null) {
            trackingDataInteractor.trackingBorrowDevice(deviceData, personData);
        } else {
            view.showToastMessage("Scan barcode dulu brooo!");
        }
    }

    public void processQRResult(String qrResult) {
        view.resetContentView();
        if (view.isBorrowedDevice()) {
            this.urlEmployee = qrResult;
            view.startQRCodeScanner();
        } else {
            if (qrResult.contains("https://www.tokopedia.com/team"))
                qrCodeInteractor.requestEmployeeData(qrResult);
            else
                view.showToastMessage("Format tidak sesuai!");
        }
    }

    public void unregistrateBorrowData(DeviceData deviceData) {
        if (urlEmployee != null) {
            trackingDataInteractor.trackingReturnDevice(deviceData, urlEmployee);
        } else {
            view.showToastMessage("Scan dulu bro!");
        }
    }

    public void resetPersonData() {
        this.personData = null;
    }


    @Override
    public void onTracked(TrackingData trackingData) {
        switch (trackingData.getActivity()) {
            case TrackingData.ACTIVITY_BORROW:
                view.renderDeviceList(trackingData.getDevice());
                view.renderDeviceDetail(trackingData.getDevice());
                break;
            case TrackingData.ACTIVITY_RETURN:
                view.showUrlEmployee(trackingData.getPerson().getName());
                break;
        }

    }

    @Override
    public void onFailTracking(TrackingData trackingData) {
        switch (trackingData.getActivity()) {
            case TrackingData.ACTIVITY_BORROW:

                break;
            case TrackingData.ACTIVITY_RETURN:

                view.showToastMessage("Data tidak sama dengan peminjam!");
                break;
        }
    }

    public interface View {

        boolean isReady();

        boolean isAlreadyLoaded();

        void setAttributeVar();

        void showSuccessResult(String employeeName);

        void showErrorResult(String employeeName);

        void stopQRCodeScanner();

        void startQRCodeScanner();

        void deviceIsBorrowed(TrackingData trackingData);

        void deviceIsAvailable();

        void renderDeviceDetail(DeviceData deviceData);

        void showToastMessage(String message);

        boolean isBorrowedDevice();

        void showUrlEmployee(String qrResult);

        void renderDeviceList(DeviceData deviceData);

        void showProgressLayout();

        void hideProgressLayout();

        void resetContentView();
    }
}
