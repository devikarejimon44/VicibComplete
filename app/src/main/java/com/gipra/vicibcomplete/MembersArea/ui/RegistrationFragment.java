package com.gipra.vicibcomplete.MembersArea.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.gipra.vicibcomplete.MembersArea.ui.Dashboard.DashBoardFragment;
import com.gipra.vicibcomplete.R;


public class RegistrationFragment extends DashBoardFragment {


    public RegistrationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (container!=null)
//        {
//            container.removeAllViewsInLayout();
//        }
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Registration");

        return view;
    }



    }



