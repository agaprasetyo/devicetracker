package com.tokopedia.devicetracker.ui.mainadmin.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.baseui.BaseFragment;
import com.tokopedia.devicetracker.ui.mainadmin.adapter.DeviceRecyclerAdminAdapter;
import com.tokopedia.devicetracker.ui.mainadmin.presenters.DeviceRecyclerAdminPresenter;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public class DeviceRecyclerAdminFragment extends BaseFragment<DeviceRecyclerAdminPresenter> implements DeviceRecyclerAdminAdapter.OnInteractionListener, DeviceRecyclerAdminPresenter.View {

    @Bind(R.id.list)
    RecyclerView recyclerView;
    DeviceRecyclerAdminAdapter adapter;

    private ActivityInteractionListener mListener;

    public static DeviceRecyclerAdminFragment newInstance() {
        return new DeviceRecyclerAdminFragment();
    }

    public DeviceRecyclerAdminFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.initialize();
    }

    @Override
    protected void initialPresenter() {
        presenter = new DeviceRecyclerAdminPresenter(getActivity(), this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_list_device;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ActivityInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ActivityInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDeleteDeviceData(DeviceData deviceData) {
        presenter.deleteDevice(deviceData);
    }

    @Override
    public void onEditDeviceData(DeviceData deviceData) {
        mListener.toEditDevice(deviceData);
    }

    @Override
    public void onDeviceLogTracking(DeviceData deviceData) {
        mListener.toDeviceLogTracking(deviceData);
    }

    @Override
    public void setAttributeVar() {
        adapter = new DeviceRecyclerAdminAdapter(getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void populateRecyclerView(List<DeviceData> deviceDatas) {
        adapter.setData(deviceDatas);
    }

    @Override
    public void openQRScannerDialog(final Dialog dialog) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.show();
            }
        });

    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void renderRecyclerItem(DeviceData device) {
        adapter.renderItemView(device);
    }

    @Override
    public void removeRecyclerItem(DeviceData device) {
        adapter.deleteItemView(device);
    }

    @Override
    public void addDeviceItem(DeviceData deviceData) {
        adapter.addEntity(deviceData);
    }


    public interface ActivityInteractionListener {

        void toEditDevice(DeviceData deviceData);

        void toDeviceLogTracking(DeviceData deviceData);
    }

}
