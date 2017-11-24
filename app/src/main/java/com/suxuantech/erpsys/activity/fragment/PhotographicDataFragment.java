package com.suxuantech.erpsys.activity.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.suxuantech.erpsys.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotographicDataFragment extends Fragment {

    public PhotographicDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.refresh_and_recyclerview, container, false);
    }
}
