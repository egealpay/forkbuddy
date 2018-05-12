package com.forkbuddy.forkbuddy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class ProfileFragment extends Fragment {

    Button editProfile, settings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editProfile = view.findViewById(R.id.btnEditInfo);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Settings Activity For Edit Info will Come Here.",
                        Toast.LENGTH_LONG).show();
            }
        });

        settings = view.findViewById(R.id.btnSettings);
       settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Settings Activity For Settings will Come Here.",
                        Toast.LENGTH_LONG).show();
            }
        });



        return view;
    }

}
