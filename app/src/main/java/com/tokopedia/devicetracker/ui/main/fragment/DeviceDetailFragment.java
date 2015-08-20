package com.tokopedia.devicetracker.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.zxing.Result;
import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.BorrowData;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.BaseFragment;
import com.tokopedia.devicetracker.ui.main.presenters.DeviceDetailPresenter;

import butterknife.Bind;
import butterknife.OnClick;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class DeviceDetailFragment extends BaseFragment implements DeviceDetailPresenter.View, ZXingScannerView.ResultHandler {
    private static final String ARG_PARAM_DEVICE_DATA = "ARG_PARAM_DEVICE_DATA";

    private DeviceData deviceData;

    private OnFragmentInteractionListener mListener;
    private DeviceDetailPresenter presenter;

    //    @Bind(R.id.qrcode_view)
//    QRCodeReaderView qrCodeReaderView;
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

    public static DeviceDetailFragment newInstance(DeviceData deviceData) {
        DeviceDetailFragment fragment = new DeviceDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM_DEVICE_DATA, deviceData);
        fragment.setArguments(args);
        return fragment;
    }

    public DeviceDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            deviceData = getArguments().getParcelable(ARG_PARAM_DEVICE_DATA);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_detail;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //   qrCodeReaderView.setOnQRCodeReadListener(this);
        presenter = new DeviceDetailPresenter(this);
        presenter.initialize();
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
        startQRCodeScanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopQRCodeScanner();
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
    public void showInProcessResult() {

    }

    @Override
    public void stopQRCodeScanner() {
        //   qrCodeReaderView.getCameraManager().stopPreview();
        zXingScannerView.stopCamera();
    }

    @Override
    public void startQRCodeScanner() {
        //   qrCodeReaderView.getCameraManager().startPreview();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera(1);
    }

    @Override
    public void deviceIsBorrowed(BorrowData borrowData) {
        layoutBack.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
        layoutBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void deviceIsAvailable() {
        layoutBack.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
        layoutBack.setVisibility(View.GONE);
    }

    @Override
    public void handleResult(Result result) {
        presenter.processQRResult(result.toString());
    }

    public interface OnFragmentInteractionListener {

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

    @OnClick(R.id.btn_borrow)
    public void borrow() {
        presenter.registrateBorrowData(deviceData);
    }


    @OnClick(R.id.btn_back)
    public void back() {
        presenter.unregistrateBorrowData(deviceData);
    }

}
