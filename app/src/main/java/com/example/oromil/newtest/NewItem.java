package com.example.oromil.newtest;

import android.graphics.Color;

public class NewItem {
    public String name;
    public String weigt;
    public Float rub;
    int color = Color.argb(0, 100, 100, 100);

    NewItem(String _name, String _weight, Float _rub) {
        name = _name;
        weigt = _weight;
        rub = _rub;
    }
}