package com.tokopedia.devicetracker.ui.main.presenters;

import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.interactors.listener.OnRequestPersonDataFinishedListener;
import com.tokopedia.devicetracker.interactors.listener.OnTrackingDataFinishedListener;
import com.tokopedia.devicetracker.interactors.interactor.QRCodeInteractor;
import com.tokopedia.devicetracker.interactors.iteractorimpl.QRCodeInteractorImpl;
import com.tokopedia.devicetracker.interactors.interactor.UserTrackingDataInteractor;
import com.tokopedia.devicetracker.interactors.iteractorimpl.UserTrackingDataInteractorImpl;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DeviceDetailPresenter extends Presenter implements OnRequestPersonDataFinishedListener, OnTrackingDataFinishedListener {
    private static final String TAG = DeviceDetailPresenter.class.getSimpleName();

    private View view;
    private QRCodeInteractor qrCodeInteractor;
    private UserTrackingDataInteractor trackingDataInteractor;

    private String urlPerson;
    private PersonData personData;

    public DeviceDetailPresenter(View view) {
        this.view = view;
        this.qrCodeInteractor = new QRCodeInteractorImpl(this);
        this.trackingDataInteractor = new UserTrackingDataInteractorImpl(this);
    }

    @Override
    public void initialize() {
        view.setAttributeVar();
        view.renderDeviceDetail();
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
    public void onPersonDataSuccess(PersonData personData) {
        this.personData = personData;
        view.enableBorrowButton();
        view.startQRCodeScanner();
        view.showPersonName(personData.getName());
        view.hideProgressLayout();
        view.showToastMessage("Hallo " + personData.getName() + ", Data Anda sudah tervalidasi. Silahkan tekan tombol pinjam.");
    }

    @Override
    public void onPersonDataError(String errorMessage) {
        view.resetContentView();
        view.startQRCodeScanner();
        view.hideProgressLayout();
        view.showToastMessage(errorMessage);
    }

    @Override
    public void onPersonDataProcess() {
        view.showProgressLayout();
    }

    public void analyzeDeviceData(DeviceData deviceData) {
        resetPersonData();
        if (deviceData != null) {
            this.urlPerson = null;
            view.resetContentView();
            if (deviceData.isBorrowed()) {
                TrackingData trackingData = trackingDataInteractor.getLastTrackingDataBydevice(deviceData);
                view.renderDeviceIsBorrowed(trackingData);
            } else {
                view.renderDeviceIsAvailable();
            }
        } else {
            view.renderDeviceNotSelected();
        }


    }

    public void borrowDevice(DeviceData deviceData) {
        if (personData != null) {
            trackingDataInteractor.trackingBorrowDevice(deviceData, personData);
        } else {
            view.showToastMessage("Scan barcode dulu brooo!");
        }
        view.disableBorrowButtn();
    }

    public void processQRResult(String qrResult) {
        view.resetContentView();
        if (qrResult.contains("https://www.tokopedia.com/team")) {
            if (view.isBorrowedDevice()) {
                this.urlPerson = qrResult;
                view.enableReturnButton();
                view.startQRCodeScanner();
            } else {
                qrCodeInteractor.requestPersonData(qrResult);
            }
        } else {
            view.showToastMessage("Format tidak sesuai!");
            view.startQRCodeScanner();
        }
    }

    public void returnDevice(DeviceData deviceData) {
        if (urlPerson != null) {
            trackingDataInteractor.trackingReturnDevice(deviceData, urlPerson);
        } else {
            view.showToastMessage("Scan dulu bro!");
        }
        view.disableReturnButton();
    }

    public void resetPersonData() {
        this.personData = null;
    }


    @Override
    public void onDataTracked(TrackingData trackingData) {
        view.renderDeviceList(trackingData.getDevice());
        view.renderDeviceDetail(trackingData.getDevice());
    }

    @Override
    public void onFailDataTracking(String failMessage) {
        view.showToastMessage(failMessage);
    }

    public interface View {

        boolean isReady();

        boolean isAlreadyLoaded();

        void setAttributeVar();

        void showPersonName(String personName);

        void stopQRCodeScanner();

        void startQRCodeScanner();

        void renderDeviceIsBorrowed(TrackingData trackingData);

        void renderDeviceIsAvailable();

        void renderDeviceNotSelected();

        void renderDeviceList(DeviceData deviceData);

        void renderDeviceDetail(DeviceData deviceData);

        void renderDeviceDetail();

        void showToastMessage(String message);

        boolean isBorrowedDevice();

        void showProgressLayout();

        void hideProgressLayout();

        void resetContentView();

        void enableReturnButton();

        void disableReturnButton();

        void enableBorrowButton();

        void disableBorrowButtn();
    }
}
