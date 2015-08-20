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

    }

    @Override
    public void pause() {

    }

    public void requestEmployeeData(String qrResult) {
        view.stopQRCodeScanner();
        qrCodeInteractor.requestEmployeeData(qrResult);
    }

    @Override
    public void onSuccess(BorrowerData borrowerData) {
        view.startQRCodeScanner();
        view.showSuccessResult(borrowerData.getName());
        this.borrowerData = borrowerData;
    }

    @Override
    public void onError(String errorMessage) {
        view.startQRCodeScanner();
        view.showErrorResult(errorMessage);
    }

    @Override
    public void onProcess() {
        view.showInProcessResult();
    }

    public void analyzeDeviceData(DeviceData deviceData) {
        if (deviceData.isBorrowed()) {
            view.deviceIsBorrowed(deviceData.getBorrowData());
        } else {
            view.deviceIsAvailable();
        }
    }

    public void registrateBorrowData(DeviceData deviceData) {
        if (borrowerData != null) {
            MainApp.getInstance().getDbService().getBorrowerData().saveData(borrowerData);
            BorrowData borrowData = new BorrowData(new Date().getTime(), borrowerData);
            MainApp.getInstance().getDbService().getBorrowData().saveData(borrowData);
            deviceData.setBorrowed(true);
            deviceData.setBorrowData(borrowData);
            MainApp.getInstance().getDbService().getDeviceData().saveData(deviceData);
            view.refreshDeviceStatus(deviceData);
        } else {
            view.showToastMessage("Scan barcode dulu brooo!");
        }
    }

    public void processQRResult(String qrResult) {
        if (view.isBorrowedDevice()) {
            this.urlEmployee = qrResult;
            view.showUrlEmployee(qrResult);
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

        void showInProcessResult();

        void stopQRCodeScanner();

        void startQRCodeScanner();

        void deviceIsBorrowed(BorrowData borrowData);

        void deviceIsAvailable();

        void refreshDeviceStatus(DeviceData deviceData);

        void showToastMessage(String message);

        boolean isBorrowedDevice();

        void showUrlEmployee(String qrResult);
    }
}
