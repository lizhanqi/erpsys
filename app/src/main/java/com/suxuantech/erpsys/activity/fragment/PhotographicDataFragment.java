package com.suxuantech.erpsys.activity.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.suxuantech.erpsys.R;
import com.yanzhenjie.fragment.NoFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotographicDataFragment extends NoFragment {
    public PhotographicDataFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photographic_data, container, false);
    }
}
