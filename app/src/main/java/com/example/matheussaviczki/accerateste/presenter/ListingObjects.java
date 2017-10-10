package com.example.matheussaviczki.accerateste.presenter;

import android.view.View;

import com.example.matheussaviczki.accerateste.R;
import com.example.matheussaviczki.accerateste.layout.ListFragment;
import com.example.matheussaviczki.accerateste.models.PersonagemModel;
import com.example.matheussaviczki.accerateste.models.PersonagemModelRealm;
import com.example.matheussaviczki.accerateste.utils.Utils;

import java.util.ArrayList;

import retrofit2.http.PUT;

/**
 * Created by Matheus Saviczki on 08/10/2017.
 */

public class ListingObjects {

    private ListFragment listFragment;

    public ListingObjects(ListFragment listFragment)
    {
        this.listFragment = listFragment;
    }
   /* public String[] ListingName(String[] str)
    {
        String[] listed = listFragment.CharactersList
    }*/

    public PersonagemModelRealm SetDetails(int index, ArrayList<PersonagemModel> list)
    {
        PersonagemModelRealm result = new PersonagemModelRealm();
        result.setName(list.get(index).getName());
        result.setHeight(list.get(index).getHeight());
        result.setMass(list.get(index).getMass());
        result.setHair_color(list.get(index).getHair_color());
        result.setSkin_color(list.get(index).getSkin_color());
        result.setEye_color(list.get(index).getEye_color());
        result.setBirth_year(list.get(index).getBirth_year());
        result.setGender(list.get(index).getGender());
        result.setCreated(list.get(index).getCreated());
        result.setEdited(list.get(index).getEdited());
        result.setUrl(list.get(index).getUrl());
        return result;
    }
}
