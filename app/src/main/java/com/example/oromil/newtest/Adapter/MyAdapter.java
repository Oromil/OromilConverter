package com.example.oromil.newtest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.oromil.newtest.NewItem;
import com.example.oromil.newtest.R;

import java.util.ArrayList;

/**
 * Created by Oromil on 02.02.2017.
 */

public class MyAdapter extends BaseAdapter {

    Context cont;
    ArrayList<NewItem> items;
    LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<NewItem> array){
        cont = context;
        items = array;
        inflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.item, parent, false);
        }

        NewItem item = getNewItem(position);

        ((TextView)view.findViewById(R.id.textView)).setText(item.name);
        ((TextView)view.findViewById(R.id.textView2)).setText(item.weigt);
        ((TextView)view.findViewById(R.id.textTemp)).setText(String.valueOf(item.rub));

        return view;
    }

    public NewItem getNewItem(int position){
        return (NewItem)getItem(position);
    }
}
