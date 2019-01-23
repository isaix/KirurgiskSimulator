package dk.dtu.isaacirani.kirurgisksimulator.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.dtu.isaacirani.kirurgisksimulator.R;

public class InstructorFragment extends Fragment {


    public InstructorFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_instructor, container, false);
    }



}
