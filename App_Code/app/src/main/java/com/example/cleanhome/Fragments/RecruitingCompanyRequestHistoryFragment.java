package com.example.cleanhome.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cleanhome.R;


public class RecruitingCompanyRequestHistoryFragment extends Fragment {

    public  RecruitingCompanyRequestHistoryFragment(){
        //constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recruiting_company_request_history, container, false);



        return view;
    }


}
