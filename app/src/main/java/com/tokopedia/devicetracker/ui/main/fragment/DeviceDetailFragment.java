package com.tokopedia.devicetracker.ui.main.fragment;

import android.app.Activity;
import android.graphics.Camera;
import android.hardware.camera2.CameraDevice;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.model.BorrowData;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.BaseFragment;
import com.tokopedia.devicetracker.ui.main.presenters.DeviceDetailPresenter;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.Bind;
import butterknife.OnClick;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class DeviceDetailFragment extends BaseFragment implements DeviceDetailPresenter.View, ZXingScannerView.ResultHandler {
    private static final String ARG_PARAM_DEVICE_DATA = "ARG_PARAM_DEVICE_DATA";

    private DeviceData deviceData;

    private OnFragmentInteractionListener mListener;
    private DeviceDetailPresenter presenter = new DeviceDetailPresenter(this);

    @Bind(R.id.et_employee_name_1)
    EditText etBorrow;
    @Bind(R.id.btn_borrow)
    Button btnBorrow;
    @Bind(R.id.et_employee_name_2)
    EditText etBack;
    @Bind(R.id.btn_back)
    Button btnBack;
    @Bind(R.id.layout_2)
    RelativeLayout layoutBack;
    @Bind(R.id.zxing_view)
    ZXingScannerView zXingScannerView;
    @Bind(R.id.tv_desc_borrow_data)
    TextView tvBorrowData;
    @Bind(R.id.layout_progress)
    RelativeLayout layoutProgress;

    public static DeviceDetailFragment newInstance(DeviceData deviceData) {
        DeviceDetailFragment fragment = new DeviceDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM_DEVICE_DATA, deviceData);
        fragment.setArguments(args);
        return fragment;
    }

    public DeviceDetailFragment() {
    }

    @OnClick(R.id.btn_borrow)
    public void borrow() {
        presenter.registrateBorrowData(deviceData);
    }

    @OnClick(R.id.btn_back)
    public void back() {
        presenter.unregistrateBorrowData(deviceData);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            deviceData = getArguments().getParcelable(ARG_PARAM_DEVICE_DATA);
        } else {
            deviceData = MainApp.getInstance().getDbService().getDeviceData().getData(1);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_detail;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.initialize();
        refreshDeviceStatus(deviceData);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean isReady() {
        return isAdded();
    }

    @Override
    public boolean isAlreadyLoaded() {
        return false;
    }

    @Override
    public void setAttributeVar() {

    }

    @Override
    public void showSuccessResult(String employeeName) {
        etBorrow.setText(employeeName);
    }

    @Override
    public void showErrorResult(String errorMessage) {
        etBorrow.setText(errorMessage);
    }

    @Override
    public void stopQRCodeScanner() {
        zXingScannerView.stopCamera();
    }

    @Override
    public void startQRCodeScanner() {
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera(1);
    }

    @Override
    public void deviceIsBorrowed(BorrowData borrowData) {
        tvBorrowData.setText("Lagi dipinjam sama " + this.deviceData.getBorrowData().getBorrowerData().getName() + "\n"
                + new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("in_ID")).format(this.deviceData.getBorrowData().getTime()));
        layoutBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void deviceIsAvailable() {
        layoutBack.setVisibility(View.GONE);
    }

    @Override
    public void handleResult(Result result) {
        presenter.processQRResult(result.toString());
    }

    public void refreshDeviceStatus(DeviceData deviceData) {
        this.deviceData = deviceData;
        presenter.analyzeDeviceData(deviceData);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isBorrowedDevice() {
        return deviceData.isBorrowed();
    }

    @Override
    public void showUrlEmployee(String qrResult) {
        etBack.setText(qrResult);
    }

    @Override
    public void renderDeviceList(DeviceData id) {
        mListener.renderListItem(id);
    }

    @Override
    public void showProgressLayout() {
        layoutProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressLayout() {
        layoutProgress.setVisibility(View.GONE);
    }

    @Override
    public void resetContentView() {
        tvBorrowData.setText("");
        etBack.setText("");
        etBorrow.setText("");
    }

    public interface OnFragmentInteractionListener {
        void renderListItem(DeviceData deviceId);
    }

}
