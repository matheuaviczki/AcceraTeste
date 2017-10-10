package com.example.matheussaviczki.accerateste.models.ImageModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Matheus Saviczki on 08/10/2017.
 */

public class DataModel {

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
