package com.example.oromil.newtest;

import android.database.Cursor;
import android.util.Log;

import com.example.oromil.newtest.network.Model.Valute;

import java.util.ArrayList;

/**
 * Created by Oromil on 06.10.2016.
 */
public class MyConverter {
    ArrayList<Float> courses;

    public MyConverter() {
        courses = new ArrayList<>();
    }

    public ArrayList getCoursesArray(Valute object) {
        courses.clear();
        courses.add(1f);
        courses.add(object.rates.uSD);
        courses.add(object.rates.gBP);
        courses.add(object.rates.eUR);
        courses.add(object.rates.aUD);
        return courses;
    }

    public void convertToObject(ArrayList<NewItem> items, Cursor c) {

        if (c.moveToFirst()) {
            int courseColIndex = c.getColumnIndex("course");
            int nameColIndex = c.getColumnIndex("name");
            do {
                NewItem item = new NewItem(c.getString(nameColIndex), "0", c.getFloat(courseColIndex));
                items.add(item);
            } while (c.moveToNext());
        } else
            Log.d("Items Array: ", "0 rows");
        c.close();
    }
}
