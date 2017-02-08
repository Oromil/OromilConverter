package com.example.oromil.newtest.network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Oromil on 01.08.2016.
 */
public class Rates {
    @SerializedName("USD")
    @Expose
    public Float uSD;
    @SerializedName("GBP")
    @Expose
    public Float gBP;
    @SerializedName("EUR")
    @Expose
    public Float eUR;
    @SerializedName("AUD")
    @Expose
    public Float aUD;

}

