package com.example.matheussaviczki.accerateste.utils;

import android.graphics.Bitmap;

import com.example.matheussaviczki.accerateste.layout.CaptureQrFragment;
import com.example.matheussaviczki.accerateste.models.PersonagemModel;
import com.example.matheussaviczki.accerateste.tasks.CaptureQR;

/**
 * Created by Matheus Saviczki on 08/10/2017.
 */

public class Params {

    private CaptureQrFragment fragment;
    private String bitmap;
    private String city;
    private PersonagemModel model;
    private Bitmap image;

    public CaptureQrFragment getFragment() {
        return fragment;
    }

    public void setFragment(CaptureQrFragment fragment) {
        this.fragment = fragment;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public PersonagemModel getModel() {
        return model;
    }

    public void setModel(PersonagemModel model) {
        this.model = model;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
