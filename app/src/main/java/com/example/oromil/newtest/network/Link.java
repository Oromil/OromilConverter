package com.example.oromil.newtest.network;

import com.example.oromil.newtest.network.Model.Valute;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Oromil on 27.10.2016.
 */

public interface Link {
    @GET("/latest?base=RUB")
    Observable<Valute> getCourses();
}
