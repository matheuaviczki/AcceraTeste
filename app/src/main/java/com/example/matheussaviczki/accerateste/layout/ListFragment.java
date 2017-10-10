package com.example.matheussaviczki.accerateste.layout;

import android.content.pm.FeatureInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matheussaviczki.accerateste.R;
import com.example.matheussaviczki.accerateste.adapter.CustomAdapter;
import com.example.matheussaviczki.accerateste.models.PersonagemModel;
import com.example.matheussaviczki.accerateste.models.PersonagemModelRealm;
import com.example.matheussaviczki.accerateste.tasks.CaptureQR;
import com.example.matheussaviczki.accerateste.utils.PosterFilmsParams;
import com.example.matheussaviczki.accerateste.utils.Utils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Matheus Saviczki on 04/10/2017.
 */

public class ListFragment extends Fragment {


    public ListFragment(){}

    public static ArrayList<PersonagemModelRealm> CharactersList = new ArrayList<>();
    private String[] getNameList;
    private String[] getUrlList;
    private ListView listView;
    private LinearLayout linear_films;
    private LinearLayout layoutDetails;
    private LinearLayout layout_posters;
    private TextView txtNome;
    private TextView txtAltura;
    private TextView txtPeso;
    private TextView txtCabelo;
    private TextView txtPele;
    private TextView txtOlhos;
    private TextView txtAno;
    private TextView txtGenero;
    private TextView txtCriacao;
    private TextView txtEdicao;
    private TextView txtUrl;
    private TextView txtNomeBar;
    private TextView txtFilmBar;
    private ImageView imgPersonagem;
    private ImageView set_poster_layout;
    private ImageView btnCloseView;
    private ImageView btnCloseViewFilm;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        listView = (ListView) rootView.findViewById(R.id.list_result);
        layoutDetails = (LinearLayout)rootView.findViewById(R.id.layout_details);
        linear_films = (LinearLayout)rootView.findViewById(R.id.layout_films);
        layout_posters = (LinearLayout)rootView.findViewById(R.id.layout_posters);
        txtNome = (TextView)rootView.findViewById(R.id.txtNome);
        txtAltura = (TextView)rootView.findViewById(R.id.txtAltura);
        txtPeso = (TextView)rootView.findViewById(R.id.txtPeso);
        txtCabelo = (TextView)rootView.findViewById(R.id.txtCabelo);
        txtPele = (TextView)rootView.findViewById(R.id.txtPele);
        txtOlhos = (TextView)rootView.findViewById(R.id.txtOlhos);
        txtAno = (TextView)rootView.findViewById(R.id.txtAno);
        txtGenero = (TextView)rootView.findViewById(R.id.txtGenero);
        txtCriacao = (TextView)rootView.findViewById(R.id.txtCriacao);
        txtEdicao = (TextView)rootView.findViewById(R.id.txtEdicao);
        txtUrl = (TextView)rootView.findViewById(R.id.txtUrl);
        txtNomeBar = (TextView)rootView.findViewById(R.id.txtNomeBar);
        txtFilmBar = (TextView)rootView.findViewById(R.id.txtFilmBar);
        btnCloseView = (ImageView)rootView.findViewById(R.id.btnCloseView);
        btnCloseViewFilm = (ImageView)rootView.findViewById(R.id.btnCloseViewFilm);
        imgPersonagem = (ImageView)rootView.findViewById(R.id.imgPersonagem);
        set_poster_layout = (ImageView)rootView.findViewById(R.id.set_poster_layout);
        Shader shader = Utils.ReturnGradientGrey();
        txtNomeBar.getPaint().setShader(shader);
        txtFilmBar.getPaint().setShader(shader);


        MediaPlayer imperialTheme = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.imperialmarch);
        imperialTheme.start();
        ListingObjects();
        CustomAdapter customAdapter = new CustomAdapter(getActivity(),getNameList, getUrlList);
        listView.setAdapter(customAdapter);
        ClickOnItem();
        CloseView();

   //     String str = Utils.GetImageURL("http://digital.hammacher.com/Items/77944/77944_1000x1000.jpg", getActivity().getApplicationContext());
        return rootView;
    }


    private void ClickOnItem()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bitmap bmp = Utils.ConvertStringToBitMap(CharactersList.get(position).getImage());
                imgPersonagem.setImageBitmap(bmp);
                txtNomeBar.setText(CharactersList.get(position).getName());
                txtNome.setText(Utils.MixBoldWithNormal(getString(R.string.location), " " + CharactersList.get(position).getCity()));
                txtAltura.setText(Utils.MixBoldWithNormal(getString(R.string.heigth), " " + CharactersList.get(position).getHeight()));
                txtPeso.setText(Utils.MixBoldWithNormal(getString(R.string.mass), " " + CharactersList.get(position).getMass()));
                txtCabelo.setText(Utils.MixBoldWithNormal(getString(R.string.hair_color), " " + CharactersList.get(position).getHair_color()));
                txtPele.setText(Utils.MixBoldWithNormal(getString(R.string.skin_color), " " + CharactersList.get(position).getSkin_color()));
                txtOlhos.setText(Utils.MixBoldWithNormal(getString(R.string.eye_color), " " + CharactersList.get(position).getEye_color()));
                txtAno.setText(Utils.MixBoldWithNormal(getString(R.string.birth_year), " " + CharactersList.get(position).getBirth_year()));
                txtGenero.setText(Utils.MixBoldWithNormal(getString(R.string.gender), " " + CharactersList.get(position).getGender()));
                txtCriacao.setText(Utils.MixBoldWithNormal(getString(R.string.created), " " + CharactersList.get(position).getCreated()));
                txtEdicao.setText(Utils.MixBoldWithNormal(getString(R.string.edited), " " + CharactersList.get(position).getEdited()));
                txtUrl.setText(Utils.MixBoldWithNormal(getString(R.string.url), " " + CharactersList.get(position).getUrl()));
                CreateFilmsIcons(CharactersList.get(position).getFilms());
                listView.setVisibility(View.GONE);
                layoutDetails.setVisibility(View.VISIBLE);

            }
        });
    }

    private void ListingObjects()
    {
        getNameList = new String[CharactersList.size()];
        getUrlList = new String[CharactersList.size()];

        for(int i = 0; i < CharactersList.size(); i++)
        {
            getNameList[i] = CharactersList.get(i).getName();
            getUrlList[i] = CharactersList.get(i).getUrl();
        }

    }

    private void CreateFilmsIcons(final String films)
    {
        String[] separeFilms = films.split("/");
        linear_films.removeAllViews();
        for (final String separeFilm : separeFilms) {
            ImageView dinamicImageview = new ImageView(getActivity().getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120, 120);
            params.setMargins(20, 5, 5, 0);
            dinamicImageview.setLayoutParams(params);
            int index = Utils.GetFilmIcon(separeFilm);
            dinamicImageview.setImageResource(index);
            dinamicImageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PosterFilmsParams filmsParams = Utils.GetFilmInformations(separeFilm);
                    set_poster_layout.setImageResource(filmsParams.getDrawable());
                    txtFilmBar.setText(getString(filmsParams.getFilmName()));
                    layoutDetails.setVisibility(View.GONE);
                    layout_posters.setVisibility(View.VISIBLE);
                }
            });
            linear_films.addView(dinamicImageview);
        }

    }

    private void CloseView()
    {
        btnCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutDetails.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        });

        btnCloseViewFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_posters.setVisibility(View.GONE);
                layoutDetails.setVisibility(View.VISIBLE);
            }
        });
    }
}
