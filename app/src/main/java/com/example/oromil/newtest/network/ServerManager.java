package com.example.oromil.newtest.network;

import android.util.Log;
import com.example.oromil.newtest.network.Model.Valute;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by Oromil on 03.10.2016.
 */
public class ServerManager {
    private static final String BASE_URL = "https://api.fixer.io";
    private static BehaviorSubject<Valute> observableModelsList;
    private static Observable<Valute> observableRetrofit;
    private static Subscription subscription;

    public ServerManager(){
    }

    public static void init() {
        Log.d("INIT: ", "init");

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();

        Link apiService = retrofit.create(Link.class);

        observableRetrofit = apiService.getCourses();
    }

    public static void resetModelsObservable() {
        observableModelsList = BehaviorSubject.create();

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = observableRetrofit.subscribe(new Subscriber<Valute>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                observableModelsList.onError(e);
            }

            @Override
            public void onNext(Valute valute) {
                observableModelsList.onNext(valute);
            }
        });
    }


    public static Observable<Valute> getModelsObservable() {
        if (observableModelsList == null) {
            resetModelsObservable();
        }
        return observableModelsList;
    }

}
