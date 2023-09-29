package com.example.cleanhome.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cleanhome.R;


public class CleaningCompanyManageRequestFragment extends Fragment {

    public CleaningCompanyManageRequestFragment(){
        //constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cleaning_company_manage_request, container, false);




        return view;
    }
}