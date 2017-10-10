package com.example.matheussaviczki.accerateste.models;

import android.content.Context;
import android.util.Log;

import com.example.matheussaviczki.accerateste.layout.ListFragment;
import com.example.matheussaviczki.accerateste.utils.Utils;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Matheus Saviczki on 07/10/2017.
 */

public class RealmConfig {

    private Realm realm;
    private Context context;

    public RealmConfig(Context context){
        this.context = context;
    }

    public void InitializeRealm()
    {
        realm = Realm.getInstance(context);
    }

    public void SaveCharactersDB(PersonagemModel model, String image, String city)
    {
        realm.beginTransaction();
        PersonagemModelRealm realmModel = realm.createObject(PersonagemModelRealm.class);

        realmModel.setName(Utils.ToUpperCase(model.getName()));
        realmModel.setHeight(Utils.ToUpperCase(model.getHeight()));
        realmModel.setMass(Utils.ToUpperCase(model.getMass()));
        realmModel.setHair_color(Utils.ToUpperCase(model.getHair_color()));
        realmModel.setSkin_color(Utils.ToUpperCase(model.getSkin_color()));
        realmModel.setEye_color(Utils.ToUpperCase(model.getEye_color()));
        realmModel.setBirth_year(Utils.ToUpperCase(model.getBirth_year()));
        realmModel.setGender(Utils.ToUpperCase(model.getGender()));
        realmModel.setHomeworld(Utils.ToUpperCase(model.getHomeworld()));
        realmModel.setCreated(Utils.ToUpperCase(model.getCreated()));
        realmModel.setEdited(Utils.ToUpperCase(model.getEdited()));
        realmModel.setUrl(model.getUrl());
        realmModel.setImage(image);
        realmModel.setCity(city);
        realmModel.setFilms(Utils.SaveFilmsOnRealm(model.getFilms()));
        realm.commitTransaction();
    }

    public void ListCharacters()
    {
        RealmResults<PersonagemModelRealm> results = realm.where(PersonagemModelRealm.class).findAll();
        ListFragment.CharactersList.clear();
        results.sort("name");

        for(PersonagemModelRealm model : results)
        {
            ListFragment.CharactersList.add(model);
        }
    }
}
