package com.example.matheussaviczki.accerateste.tasks;

import com.example.matheussaviczki.accerateste.models.ImageModel.DataModel;
import com.example.matheussaviczki.accerateste.models.PersonagemModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Matheus Saviczki on 07/10/2017.
 */

public interface WebService {

    interface Character{
        @GET()
        Call<PersonagemModel> getCharacters(
                @Url String url
        );

    }

    interface CharacterImage{
        @GET()
        Call<DataModel> getImage(
                @Url String url
        );
    }

}
