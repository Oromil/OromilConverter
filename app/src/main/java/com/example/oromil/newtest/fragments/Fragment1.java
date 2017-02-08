package com.example.oromil.newtest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.oromil.newtest.R;

/**
 * Created by Oromil on 06.02.2017.
 */

public class Fragment1 extends Fragment {

    public ListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_1, null);

        list = (ListView) v.findViewById(R.id.listView);

        return v;
    }


}
