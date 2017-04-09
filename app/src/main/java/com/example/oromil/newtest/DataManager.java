package com.example.oromil.newtest;

import android.content.ContentValues;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.oromil.newtest.Adapter.MyAdapter;
import com.example.oromil.newtest.database.DataBase;
import com.example.oromil.newtest.network.Model.Valute;
import com.example.oromil.newtest.network.ServerManager;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Oromil on 29.09.2016.
 */
public class DataManager {

    private DataBase db;
    private ContentValues values;
    private MyConverter converter;
    private Context cont;
    private String[] names;
    private ListView list;
    private EditText editText;
    ProgressBar progressBar;

    Subscription subscription;

    private MyAdapter mAdapter;

    DataManager(Context context, ListView lv, EditText et, ProgressBar pB) {
        cont = context;
        converter = new MyConverter();
        db = new DataBase(context);
        values = new ContentValues();
        names = cont.getResources().getStringArray(R.array.names);
        list = lv;
        editText = et;
        progressBar = pB;
        ServerManager.init();
    }


    public void save() {

        progressBar.setVisibility(ProgressBar.VISIBLE);

        ServerManager.resetModelsObservable();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = ServerManager.getModelsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Valute>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(cont, "Проверьте подключение к сети", Toast.LENGTH_LONG).show();
                        Log.d("Status: ", "Error");
                        showItems();
                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                    }

                    @Override
                    public void onNext(Valute valute) {
//                        progressBar.setVisibility(ProgressBar.VISIBLE);
                        Log.d("DB_Save", "sucsess response");
                        converter.getCoursesArray(valute);
                        db.openDB();
                        db.clearDB();
                        insertValues();
                        Log.d("DB_Save", "save sucsess");
                        db.closeDB();
                        showItems();
                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                    }
                });
    }

    private void insertValues() {
        for (int i = 0; i < converter.courses.size(); i++) {
            values.put(db.KEY_VALUTE, names[i]);
            values.put(db.KEY_COURSE, converter.courses.get(i));
            db.insertItem(values);
        }
    }

    private ArrayList<NewItem> getItems() {
        ArrayList<NewItem> items = new ArrayList<>();
        db.openDB();
        converter.convertToObject(items, db.get());
        db.closeDB();
        return items;
    }

    private void showItems() {
        ArrayList<NewItem> items = getItems();
        mAdapter = new MyAdapter(cont, items);
        list.setAdapter(mAdapter);
    }

    public void setListener() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setValue(position);
                textChange(position);
            }
        });
    }

    private void setValue(int position) {
        if (editText.getText().toString().isEmpty())
            mAdapter.getNewItem(position).weigt = "0";
        else {
            if (editText.getText().toString().equals(".")) {
                mAdapter.getNewItem(position).weigt = "0.";
                editText.setText("0.");
                editText.setSelection(editText.getText().length());
            } else mAdapter.getNewItem(position).weigt = String.valueOf(editText.getText());
        }

        for (int j = 0; j < mAdapter.getCount(); j++) {
            if (j != position)
                calculation(j, position);
        }
        list.setAdapter(mAdapter);
    }

    private void textChange(final int position) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setValue(position);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void calculation(int i, int position) {
        Float rubs =
                (Float.parseFloat(mAdapter.getNewItem(position).weigt) /
                        mAdapter.getNewItem(position).rub);
        mAdapter.getNewItem(i).weigt = String.format("%.2f", (rubs *
                mAdapter.getNewItem(i).rub));
    }

}
