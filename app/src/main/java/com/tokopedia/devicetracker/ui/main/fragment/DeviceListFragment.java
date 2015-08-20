package com.tokopedia.devicetracker.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.BaseFragment;
import com.tokopedia.devicetracker.ui.main.adapter.DeviceListAdapter;
import com.tokopedia.devicetracker.ui.main.presenters.DeviceListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class DeviceListFragment extends BaseFragment implements DeviceListPresenter.View, DeviceListAdapter.OnItemClickListener {

    @Bind(R.id.list)
    RecyclerView recyclerView;

    private OnFragmentInteractionListener mListener;
    private DeviceListPresenter presenter;

    private DeviceListAdapter adapter;

    public static DeviceListFragment newInstance() {
        return new DeviceListFragment();
    }

    public DeviceListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new DeviceListPresenter(this);
        presenter.initialize();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_device_list;
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
    public void populateListView() {
        List<DeviceData> deviceDataList = presenter.getDeviceData();
        for (DeviceData deviceData : deviceDataList) {
            adapter.add(deviceData);
        }
    }

    @Override
    public void initialView() {
        adapter = new DeviceListAdapter(this, new ArrayList<DeviceData>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDeviceListItemClicked(DeviceData deviceData) {

    }

    @Override
    public void onItemClick(View view, int position) {
        mListener.updateDetailFragment(adapter.getItem(position));
    }


    public interface OnFragmentInteractionListener {
        void updateDetailFragment(DeviceData deviceData);
    }

}