package com.example.oromil.newtest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.oromil.newtest.R;

/**
 * Created by Oromil on 06.02.2017.
 */

public class Fragment2 extends Fragment {

    public EditText editText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, null);

        editText = (EditText) v.findViewById(R.id.editText);

        return v;
    }
}
