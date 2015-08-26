package com.tokopedia.devicetracker.ui.administration.presenters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.interactors.iteractorimpl.AdminTrackingDataInteractorImpl;
import com.tokopedia.devicetracker.interactors.listener.OnTrackingDataFinishedListener;
import com.tokopedia.devicetracker.ui.BaseActivity;
import com.tokopedia.devicetracker.ui.administration.activity.FormDeviceActivity;
import com.tokopedia.devicetracker.ui.administration.activity.MainAdminActivity;
import com.tokopedia.devicetracker.ui.administration.dialog.QRScannerValidatorAdminDialog;
import com.tokopedia.devicetracker.ui.main.presenters.Presenter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public class FormDevicePresenter extends Presenter implements OnTrackingDataFinishedListener<TrackingData, String> {
    private static final String TAG = FormDevicePresenter.class.getSimpleName();
    private AdminTrackingDataInteractorImpl adminTrackingDataInteractor;
    private DeviceData deviceDataOld;

    private View view;
    private Activity activity;
    private String imgAvatarUrl;

    public FormDevicePresenter(Activity activity, View view) {
        this.activity = activity;
        this.view = view;
        this.adminTrackingDataInteractor = new AdminTrackingDataInteractorImpl(this);
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
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case BaseActivity.REQUEST_CAMERA:

                    try {
                        File fileFinal = new File(imgAvatarUrl);
                        view.showImagePreviewFromCamera(fileFinal.getAbsolutePath());
                    } catch (Exception e) {
                        view.showToastMessage("Gagal mengambil gambar, Coba lagi!");
                        imgAvatarUrl = setImageFileAvatar().getAbsolutePath();
                    }
                    break;
            }
        }
    }

    public void initialBundleData(Bundle extras) {
        if (extras != null) {
            DeviceData deviceData = extras.getParcelable(FormDeviceActivity.EXTRA_DEVICE_DATA);
            if (deviceData != null) {
                deviceDataOld = deviceData;
                view.renderDeviceDataEdit(deviceDataOld);
            }

        } else {
            view.renderDeviceDataNew();
        }
    }

    public android.view.View.OnClickListener onBtnSaveClicked() {
        return new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                DeviceData deviceData;
                if (deviceDataOld != null) {
                    deviceData = deviceDataOld;
                    String deviceName = view.getDeviceNameFromForm();
                    if (!deviceName.equals("")) {
                        deviceData.setDeviceName(deviceName);
                    }
                    String deviceModel = view.getDeviceModelFromForm();
                    if (!deviceModel.equals("")) {
                        deviceData.setDeviceModel(deviceModel);
                    }
                    String deviceDesc = view.getDeviceDescFromForm();
                    if (!deviceDesc.equals("")) {
                        deviceData.setDeviceDesc(deviceDesc);
                    }
                    if (imgAvatarUrl != null) {
                        deviceData.setDevicePicPath(imgAvatarUrl);
                    }
                    updateDevice(deviceData);
                } else {
                    deviceData = new DeviceData();
                    deviceData.setBorrowed(false);
                    deviceData.setnStatus(DeviceData.STATUS_ACTIVE);
                    deviceData.setDeviceDesc(view.getDeviceDescFromForm());
                    deviceData.setDeviceModel(view.getDeviceModelFromForm());
                    deviceData.setDeviceName(view.getDeviceNameFromForm());
                    if (imgAvatarUrl != null) {
                        deviceData.setDevicePicPath(imgAvatarUrl);
                    } else {
                        deviceData.setDevicePicPath("");
                    }
                    Integer deviceId = view.getDeviceIdFromForm();
                    if (deviceId != null) {
                        deviceData.setId(view.getDeviceIdFromForm());
                        addDevice(deviceData);
                    } else {
                        view.showToastMessage("Device ID Salah Format!");
                    }
                }
            }
        };
    }

    private void updateDevice(final DeviceData deviceData) {
        view.openQRScannerDialog(new QRScannerValidatorAdminDialog(activity, new QRScannerValidatorAdminDialog.Callback() {
            @Override
            public void onSuccessValidation(PersonData personData) {
                adminTrackingDataInteractor.trackingDataUpdateDevice(deviceData, personData);
            }

            @Override
            public void onErrorValidation(String message) {
                view.showToastMessage("Gagal Validasi");
            }
        }));
    }


    private void addDevice(final DeviceData deviceData) {
        view.openQRScannerDialog(new QRScannerValidatorAdminDialog(activity, new QRScannerValidatorAdminDialog.Callback() {
            @Override
            public void onSuccessValidation(PersonData personData) {
                adminTrackingDataInteractor.trackingDataAddDevice(deviceData, personData);
            }

            @Override
            public void onErrorValidation(String message) {
                view.showToastMessage("Gagal Validasi");
            }
        }));
    }

    @Override
    public void onDataTracked(TrackingData successResultObj) {
        Intent data = new Intent();
        data.putExtra(FormDeviceActivity.EXTRA_DEVICE_DATA, successResultObj.getDevice());
        view.finishWithActivityResult(data);
    }

    @Override
    public void onFailDataTracking(String failedResultObj) {
        view.showToastMessage(failedResultObj);
    }

    public android.view.View.OnClickListener onImagePreviewClicked() {
        return new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                view.navigateToCamera(getIntentCameraCapture());
            }
        };
    }

    private Intent getIntentCameraCapture() {
        Intent camera = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File image = setImageFileAvatar();
        if (image != null) {
            imgAvatarUrl = image.getAbsolutePath();
            Uri uri = Uri.fromFile(image);
            camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        return camera;
    }

    private File setImageFileAvatar() {
        File imagePath = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "device_tracking/device_image");
        if (!imagePath.exists()) {
            if (!imagePath.mkdirs()) {

            } else {

            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File image = new File(imagePath, "DEVICE" + timeStamp + ".jpg");

        if (!image.exists()) {
            try {
                image.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }


    public interface View {

        void setAttributeVar();

        void renderDeviceDataEdit(DeviceData deviceData);

        void renderDeviceDataNew();

        String getDeviceNameFromForm();

        String getDeviceModelFromForm();

        String getDeviceDescFromForm();

        void openQRScannerDialog(Dialog dialog);

        void showToastMessage(String message);

        void finishWithActivityResult(Intent intentData);

        Integer getDeviceIdFromForm();

        void navigateToCamera(Intent intentCameraCapture);

        void showImagePreviewFromCamera(String absolutePath);
    }
}
