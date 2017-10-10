package com.example.matheussaviczki.accerateste.models.ImageModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Matheus Saviczki on 08/10/2017.
 */

public class Data {

    @SerializedName("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
