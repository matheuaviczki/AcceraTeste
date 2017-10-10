package com.example.matheussaviczki.accerateste.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.matheussaviczki.accerateste.layout.CaptureQrFragment;
import com.example.matheussaviczki.accerateste.models.ImageModel.DataModel;
import com.example.matheussaviczki.accerateste.presenter.PresenterCaptureQR;
import com.example.matheussaviczki.accerateste.tasks.CaptureQR;
import com.example.matheussaviczki.accerateste.tasks.WebService;
import com.example.matheussaviczki.accerateste.utils.Params;
import com.example.matheussaviczki.accerateste.utils.Utils;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Matheus Saviczki on 07/10/2017.
 */

public class WebServiceConsum {

    private static final String URL_BASE_CHARACTERS = "https://swapi.co/api/people/";
    private static final String URL_BASE_FILMS = "https://swapi.co/api/films/";
    private static final String URL_BASE_IMAGE = "https://api.qwant.com/api/search/";
    private Params params;

    public static PersonagemModel GetPersonagemModel(String index, final CaptureQrFragment fragment, final String cityHour) {
        final PersonagemModel model = new PersonagemModel();

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE_CHARACTERS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WebService.Character webService = retrofit.create(WebService.Character.class);
            Call<PersonagemModel> requestPersonagem = webService.getCharacters(index);

            requestPersonagem.enqueue(new Callback<PersonagemModel>() {
                @Override
                public void onResponse(Call<PersonagemModel> call, Response<PersonagemModel> response) {
                    PersonagemModel personagemModel = response.body();

                    if (personagemModel != null) {
                        model.setName(personagemModel.getName());
                        model.setHeight(personagemModel.getHeight());
                        model.setMass(personagemModel.getMass());
                        model.setHair_color(personagemModel.getHair_color());
                        model.setSkin_color(personagemModel.getSkin_color());
                        model.setEye_color(personagemModel.getEye_color());
                        model.setBirth_year(personagemModel.getBirth_year());
                        model.setGender(personagemModel.getGender());
                        model.setHomeworld(personagemModel.getHomeworld());
                        model.setCreated(personagemModel.getCreated());
                        model.setEdited(personagemModel.getEdited());
                        model.setFilms(personagemModel.getFilms());
                        model.setUrl(personagemModel.getUrl());

                        GetPersonagemImage(fragment, model, cityHour);


                    }


                }

                @Override
                public void onFailure(Call<PersonagemModel> call, Throwable t) {
                    CaptureQR.View capture = fragment;
                    capture.Refresh();
                }
            });
        } catch (Exception ex) {
            return null;
        }

        return model;
    }

    private static void SaveDB(String imageString, final CaptureQrFragment fragment, final PersonagemModel model, String city)
    {
        RealmConfig realmConfig = new RealmConfig(fragment.getActivity().getApplicationContext());
        realmConfig.InitializeRealm();
        realmConfig.SaveCharactersDB(model,imageString, city);
        CaptureQR.View capture = fragment;
        capture.Refresh();
    }

    private static void SaveDB(final CaptureQrFragment fragment, final PersonagemModel model)
    {
        RealmConfig realmConfig = new RealmConfig(fragment.getActivity().getApplicationContext());
        realmConfig.InitializeRealm();
        CaptureQR.View capture = fragment;
        capture.Refresh();
    }

    public static void GetPersonagemImage(final CaptureQrFragment fragment, final PersonagemModel model, final String city) {

        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE_IMAGE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WebService.CharacterImage webService = retrofit.create(WebService.CharacterImage.class);
            Call<DataModel> requestPersonagem = webService.getImage("images?count=10&offset=1&q=" + model.getName());

            requestPersonagem.enqueue(new Callback<DataModel>() {
                @Override
                public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                    String str = call.toString();
                    DataModel result = response.body();
                    String item = result.getData().getResult().getItems().get(0).getMedia();
                    Params params = new Params();
                    params.setBitmap(item);
                    params.setFragment(fragment);
                    params.setModel(model);
                    params.setCity(city);
                    new DownloadImageUrl().execute(params);

                }

                @Override
                public void onFailure(Call<DataModel> call, Throwable t) {
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static class DownloadImageUrl extends AsyncTask<Params, Void, Params>
    {

        @Override
        protected void onPostExecute(Params params) {
            super.onPostExecute(params);

            String StringImage = Utils.ConvnertBitmapToString(params.getImage());
            SaveDB(StringImage, params.getFragment(), params.getModel(), params.getCity());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Params doInBackground(Params... params) {

            Params params_returned =params[0];

            String url = params[0].getBitmap();
            Bitmap geratedBitmap = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                geratedBitmap = BitmapFactory.decodeStream(in);
                params_returned.setImage(geratedBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return params_returned;
        }
    }

}

