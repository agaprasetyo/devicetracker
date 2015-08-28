package com.tokopedia.devicetracker.ui.mainuser.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.baseui.BaseFragment;
import com.tokopedia.devicetracker.ui.mainuser.adapter.DeviceRecyclerUserAdapter;
import com.tokopedia.devicetracker.ui.mainuser.presenters.DeviceRecyclerUserPresenter;

import butterknife.Bind;

public class DeviceRecyclerUserFragment extends BaseFragment<DeviceRecyclerUserPresenter> implements DeviceRecyclerUserPresenter.View, DeviceRecyclerUserAdapter.OnInteractionListener {

    @Bind(R.id.list)
    RecyclerView recyclerView;

    private OnFragmentInteractionListener mListener;

    private DeviceRecyclerUserAdapter adapter;

    public static DeviceRecyclerUserFragment newInstance() {
        return new DeviceRecyclerUserFragment();
    }

    public DeviceRecyclerUserFragment() {
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
        presenter = new DeviceRecyclerUserPresenter(this);
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
                    + " must implement ActivityInteractionListener");
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
        adapter.setData(presenter.getDeviceData());
        if (adapter.getItemCount() != 0) {
            adapter.performItemClicked(0);
        }
    }

    @Override
    public void setAttributeVar() {
        adapter = new DeviceRecyclerUserAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        mListener.renderDetailDeviceData(adapter.getEntity(position));
    }

    public void renderRecyclerItem(DeviceData deviceId) {
        adapter.renderItemView(deviceId);
    }


    public interface OnFragmentInteractionListener {

        void renderDetailDeviceData(DeviceData deviceData);

    }

}
