package com.example.cleanhome.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cleanhome.R;


public class VolunteerOrganizationPublishEventFragment extends Fragment {

    public  VolunteerOrganizationPublishEventFragment(){
        //constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volunteer_organization_publish_event, container, false);



        return view;
    }


}
