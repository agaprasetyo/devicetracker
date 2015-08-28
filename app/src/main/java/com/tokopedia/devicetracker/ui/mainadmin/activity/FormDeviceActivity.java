package com.tokopedia.devicetracker.ui.mainadmin.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.baseui.BaseActivity;
import com.tokopedia.devicetracker.ui.mainadmin.presenters.FormDevicePresenter;

import butterknife.Bind;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public class FormDeviceActivity extends BaseActivity<FormDevicePresenter> implements FormDevicePresenter.View {

    public static final String EXTRA_DEVICE_DATA = "EXTRA_DEVICE_DATA";

    @Bind(R.id.iv_preview)
    ImageView ivPreview;
    @Bind(R.id.et_device_name)
    EditText etName;
    @Bind(R.id.et_device_id)
    EditText etId;
    @Bind(R.id.et_device_model)
    EditText etModel;
    @Bind(R.id.et_device_desc)
    EditText etDesc;
    @Bind(R.id.btn_save)
    Button btnSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initialize();
        presenter.initialBundleData(getIntent().getExtras());
    }

    @Override
    protected void initialPresenter() {
        presenter = new FormDevicePresenter(this, this);
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_form_device;
    }


    public static Intent factoryIntentUpdate(Activity activity, DeviceData deviceData) {
        Intent intent = new Intent(activity, FormDeviceActivity.class);
        intent.putExtra(EXTRA_DEVICE_DATA, deviceData);
        return intent;
    }

    public static Intent factoryIntentAdd(Activity activity) {
        return new Intent(activity, FormDeviceActivity.class);
    }

    @Override
    public void setAttributeVar() {
        btnSave.setOnClickListener(presenter.onBtnSaveClicked());
        ivPreview.setOnClickListener(presenter.onImagePreviewClicked());
    }

    @Override
    public void renderDeviceDataEdit(DeviceData deviceData) {
        ImageLoader.getInstance().displayImage("file://" + deviceData.getDevicePicPath(), ivPreview, presenter.getDisplayImage());
        etName.setText(deviceData.getDeviceName());
        etModel.setText(deviceData.getDeviceModel());
        etDesc.setText(deviceData.getDeviceDesc());
        etId.setText(String.valueOf(deviceData.getId()));
    }

    @Override
    public void renderDeviceDataNew() {
        etName.setText("");
        etModel.setText("");
        etDesc.setText("");
        etId.setText("");
    }

    @Override
    public String getDeviceNameFromForm() {
        return etName.getText().toString();
    }

    @Override
    public String getDeviceModelFromForm() {
        return etModel.getText().toString();
    }

    @Override
    public String getDeviceDescFromForm() {
        return etDesc.getText().toString();
    }

    @Override
    public void openQRScannerDialog(final Dialog dialog) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.show();
            }
        });
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishWithActivityResult(Intent intentData) {
        setResult(android.app.Activity.RESULT_OK, intentData);
        finish();
    }

    @Override
    public Integer getDeviceIdFromForm() {
        try {
            return Integer.parseInt(etId.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.activityResult(requestCode, resultCode, data);
    }

    @Override
    public void navigateToCamera(Intent intentCameraCapture) {
        startActivityForResult(intentCameraCapture, BaseActivity.REQUEST_CAMERA);
    }

    @Override
    public void showImagePreviewFromCamera(String absolutePath) {
        ImageLoader.getInstance().displayImage("file://" + absolutePath, ivPreview, presenter.getDisplayImage());
    }
}
