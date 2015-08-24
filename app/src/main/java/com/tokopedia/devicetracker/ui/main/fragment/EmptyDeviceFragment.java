package com.tokopedia.devicetracker.ui.main.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.ui.BaseFragment;


public class EmptyDeviceFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

    public static EmptyDeviceFragment newInstance() {
        return new EmptyDeviceFragment();
    }

    public EmptyDeviceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_empty_device;
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


    public interface OnFragmentInteractionListener {

    }

}
