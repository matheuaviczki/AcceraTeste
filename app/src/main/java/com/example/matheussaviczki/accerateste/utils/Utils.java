package com.example.matheussaviczki.accerateste.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.widget.Switch;

import com.example.matheussaviczki.accerateste.R;
import com.example.matheussaviczki.accerateste.tasks.CaptureQR;


import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Matheus Saviczki on 05/10/2017.
 */

public class Utils {

    public final static String ROUTE_CHARACTER = "https://swapi.co/api/people/";
    public final static String ROUTE_FILMS = "https://swapi.co/api/films/";

    public static Shader ReturnGradientGrey()
    {
        //Retorno cor gradiente cinza claro e cinza escuro
        Shader textShader =new LinearGradient(0, 0, 0, 20,
                new int[]{Color.parseColor("#999999"),Color.parseColor("#333333")},
                new float[]{0, 1}, Shader.TileMode.MIRROR);
        return  textShader;
    }

    public static NetworkInfo GetNetworkInfo(CaptureQR.View fragment)
    {
        //Pegar iformações de conexão
        ConnectivityManager cm = fragment.GetConnectivityManager();
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info;
    }

    public static String GetFilmIndex(String film)
    {
        String[] getIndex = film.split(ROUTE_FILMS);
        return getIndex[1];
    }

    public static String ToUpperCase(String str)
    {
        //Converter o primeiro caracter do string para Maiusculo
        String upperStr = str.substring(0,1).toUpperCase() + str.substring(1);
        return upperStr;
    }

    public static String GetTimeNow()
    {
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }

    public static String SaveFilmsOnRealm(String[] allFilms)
    {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < allFilms.length; i++)
        {
            builder.append(GetFilmIndex(allFilms[i]));
        }

        String films = builder.toString();

        return films;
    }

    public static SpannableString MixBoldWithNormal(String bold, String normal)
    {
        //Deixaar metade do texto com estilo BOLD e metade NORMAL
        SpannableString span = new SpannableString(bold + normal);
        span.setSpan(new StyleSpan(Typeface.BOLD), 0, bold.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }

    public static String ConvnertBitmapToString(Bitmap bitmap)
    {
        String covnerted;
        try
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            byte[] bytes = out.toByteArray();
            covnerted = Base64.encodeToString(bytes, Base64.DEFAULT);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            covnerted = null;
        }

        return  covnerted;
    }

    public static Bitmap ConvertStringToBitMap(String imageString){
        try {
            byte [] bytes=Base64.decode(imageString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return bitmap;
        } catch(Exception e) {
            return null;
        }
    }

    public static PosterFilmsParams GetFilmInformations(String index)
    {
        PosterFilmsParams films = new PosterFilmsParams();

        switch (index)
        {
            case  "1":
                films.setDrawable(R.drawable.rsz_starwars_one);
                films.setFilmName(R.string.filme_um);
                break;
            case  "2":
                films.setDrawable(R.drawable.rsz_starwars_two);
                films.setFilmName(R.string.filme_dois);
                break;
            case  "3":
                films.setDrawable(R.drawable.rsz_starwars_three);
                films.setFilmName(R.string.filme_tres);
                break;
            case  "4":
                films.setDrawable(R.drawable.rsz_starwars_four);
                films.setFilmName(R.string.filme_quatro);
                break;
            case  "5":
                films.setDrawable(R.drawable.rsz_starwars_five);
                films.setFilmName(R.string.filme_cinco);
                break;
            case  "6":
                films.setDrawable(R.drawable.rsz_starwars_six);
                films.setFilmName(R.string.filme_seis);
                break;
            case  "7":
                films.setDrawable(R.drawable.rsz_starwars_seven);
                films.setFilmName(R.string.filme_sete);
                break;
            default:
                films = null;
                break;
        }

        return films;

    }

    public static int GetFilmIcon(String index)
    {
        int drawable;

        switch (index)
        {
            case  "1":
                drawable = R.drawable.rsz_starwars_one;
                break;
            case  "2":
                drawable = R.drawable.rsz_starwars_two;
                break;
            case  "3":
                drawable = R.drawable.rsz_starwars_three;
                break;
            case  "4":
                drawable = R.drawable.rsz_starwars_four;
                break;
            case  "5":
                drawable = R.drawable.rsz_starwars_five;
                break;
            case  "6":
                drawable = R.drawable.rsz_starwars_six;
                break;
            case  "7":
                drawable = R.drawable.rsz_starwars_seven;
                break;
            default:
                drawable = 0;
                break;
        }
        return drawable;
    }

}
