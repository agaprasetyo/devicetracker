package com.tokopedia.devicetracker.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.ui.BaseFragment;
import com.tokopedia.devicetracker.ui.main.presenters.DeviceDetailPresenter;

import butterknife.Bind;
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
    @Bind(R.id.layout_empty)
    RelativeLayout layoutEmpty;

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
            this.deviceData = getArguments().getParcelable(ARG_PARAM_DEVICE_DATA);
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
        renderDeviceDetail(deviceData);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ActivityInteractionListener");
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
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.returnDevice(deviceData);
            }
        });

        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.borrowDevice(deviceData);
            }
        });
    }

    @Override
    public void showPersonName(String employeeName) {
        etBorrow.setText(employeeName);
    }

    @Override
    public void stopQRCodeScanner() {
        zXingScannerView.stopCamera();
    }

    @Override
    public void startQRCodeScanner() {
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    public void renderDeviceIsBorrowed(TrackingData trackingData) {
        tvBorrowData.setText(trackingData.borrowDataDescription());
        layoutBack.setVisibility(View.VISIBLE);
        layoutEmpty.setVisibility(View.GONE);
    }

    @Override
    public void renderDeviceIsAvailable() {
        layoutBack.setVisibility(View.GONE);
        layoutEmpty.setVisibility(View.GONE);
    }

    @Override
    public void renderDeviceDetail(DeviceData deviceData) {
        this.deviceData = deviceData;
        presenter.analyzeDeviceData(deviceData);
    }

    @Override
    public void handleResult(Result result) {
        presenter.processQRResult(result.toString());
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
    public void renderDeviceList(DeviceData deviceData) {
        mListener.renderListItem(deviceData);
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
        disableBorrowButtn();
        disableReturnButton();
       // tvBorrowData.setText("");
        etBorrow.setText("");
    }

    @Override
    public void renderDeviceNotSelected() {
        layoutEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void renderDeviceDetail() {
        presenter.analyzeDeviceData(this.deviceData);
    }

    @Override
    public void enableReturnButton() {
        btnBack.setEnabled(true);
    }

    @Override
    public void disableReturnButton() {
        btnBack.setEnabled(false);
    }

    @Override
    public void enableBorrowButton() {
        btnBorrow.setEnabled(true);
    }

    @Override
    public void disableBorrowButtn() {
        btnBorrow.setEnabled(false);
    }


    public interface OnFragmentInteractionListener {
        void renderListItem(DeviceData deviceId);
    }

}
