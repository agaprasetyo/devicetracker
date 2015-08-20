package com.tokopedia.devicetracker.ui.main.presenters;

import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.model.BorrowData;
import com.tokopedia.devicetracker.database.model.BorrowerData;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.interactors.OnGetEmployeeFinishedListener;
import com.tokopedia.devicetracker.ui.interactors.QRCodeInteractor;
import com.tokopedia.devicetracker.ui.interactors.QRCodeInteractorImpl;

import java.util.Date;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DeviceDetailPresenter extends Presenter implements OnGetEmployeeFinishedListener {
    private static final String TAG = DeviceDetailPresenter.class.getSimpleName();

    private View view;
    private QRCodeInteractor qrCodeInteractor;
    private BorrowerData borrowerData;
    private String urlEmployee;

    public DeviceDetailPresenter(View view) {
        this.view = view;
        this.qrCodeInteractor = new QRCodeInteractorImpl(this);
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
    public void onSuccess(BorrowerData borrowerData) {
        this.borrowerData = borrowerData;
        view.startQRCodeScanner();
        view.showSuccessResult(borrowerData.getName());
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
        if (deviceData.isBorrowed()) {
            view.deviceIsBorrowed(deviceData.getBorrowData());
        } else {
            view.deviceIsAvailable();
        }
    }

    public void registrateBorrowData(DeviceData deviceData) {
        if (borrowerData != null) {
            BorrowData borrowData = new BorrowData();
            borrowData.setBorrowerData(borrowerData);
            borrowData.setTime(new Date().getTime());
            MainApp.getInstance().getDbService().getBorrowerData().saveData(borrowerData);
            MainApp.getInstance().getDbService().getBorrowData().saveData(borrowData);
            deviceData.setBorrowed(true);
            deviceData.setBorrowData(borrowData);
            MainApp.getInstance().getDbService().getDeviceData().saveData(deviceData);
            view.refreshDeviceStatus(deviceData);
            view.renderDeviceList(deviceData);
        } else {
            view.showToastMessage("Scan barcode dulu brooo!");
        }
    }

    public void processQRResult(String qrResult) {
        view.resetContentView();
        if (view.isBorrowedDevice()) {
            this.urlEmployee = qrResult;
            view.showUrlEmployee(qrResult);
            view.startQRCodeScanner();
        } else {
            if (qrResult.contains("https://www.tokopedia.com/team"))
                qrCodeInteractor.requestEmployeeData(qrResult);
            else
                view.showToastMessage("Format tidak sesuai!");
        }
    }

    public void unregistrateBorrowData(DeviceData deviceData) {
        if (urlEmployee != null & deviceData.getBorrowData().getBorrowerData().getUrlEmployee().equals(this.urlEmployee)) {
            deviceData.setBorrowed(false);
            deviceData.setBorrowData(null);
            MainApp.getInstance().getDbService().getDeviceData().saveData(deviceData);
            view.refreshDeviceStatus(deviceData);
        } else {
            view.showToastMessage("Data beda nih coy!");
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

        void deviceIsBorrowed(BorrowData borrowData);

        void deviceIsAvailable();

        void refreshDeviceStatus(DeviceData deviceData);

        void showToastMessage(String message);

        boolean isBorrowedDevice();

        void showUrlEmployee(String qrResult);

        void renderDeviceList(DeviceData id);

        void showProgressLayout();

        void hideProgressLayout();

        void resetContentView();
    }
}
