package com.tokopedia.devicetracker.ui.administration.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.BaseFragment;
import com.tokopedia.devicetracker.ui.administration.adapter.DeviceRecyclerAdapter;
import com.tokopedia.devicetracker.ui.administration.presenters.DeviceRecyclerAdminPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class DeviceRecyclerAdminFragment extends BaseFragment implements DeviceRecyclerAdapter.OnInteractionListener, DeviceRecyclerAdminPresenter.View {

    @Bind(R.id.list)
    RecyclerView recyclerView;
    DeviceRecyclerAdapter adapter;

    private ActivityInteractionListener mListener;
    private DeviceRecyclerAdminPresenter presenter;


    public static DeviceRecyclerAdminFragment newInstance() {
        return new DeviceRecyclerAdminFragment();
    }

    public DeviceRecyclerAdminFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DeviceRecyclerAdminPresenter(getActivity(), this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.initialize();
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
    public void onDeviceListItemClicked(int position) {

    }

    @Override
    public void onDeleteDeviceData(DeviceData deviceData) {
        presenter.deleteDevice(deviceData);
    }

    @Override
    public void setAttributeVar() {
        adapter = new DeviceRecyclerAdapter(getActivity(), this, new ArrayList<DeviceData>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void populateRecyclerView(List<DeviceData> deviceDatas) {
        for (DeviceData deviceData : deviceDatas) {
            adapter.add(deviceData);
        }
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


    public interface ActivityInteractionListener {

    }

}
