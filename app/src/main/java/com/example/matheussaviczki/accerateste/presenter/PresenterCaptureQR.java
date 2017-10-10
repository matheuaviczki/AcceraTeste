package com.example.matheussaviczki.accerateste.presenter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;

import com.example.matheussaviczki.accerateste.layout.CaptureQrFragment;
import com.example.matheussaviczki.accerateste.tasks.CaptureQR;
import com.example.matheussaviczki.accerateste.utils.GetLocation;
import com.example.matheussaviczki.accerateste.utils.Utils;

/**
 * Created by Matheus Saviczki on 05/10/2017.
 */

public class PresenterCaptureQR implements CaptureQR.Presenter{

    CaptureQR.View captureQrFragment;


    public PresenterCaptureQR(CaptureQR.View captureQrFragment)
    {
        this.captureQrFragment = captureQrFragment;
    }

    @Override
    public String GetCityHour(CaptureQrFragment fragment)
    {
        String city;
        GetLocation getloc = new GetLocation(fragment.getActivity().getApplicationContext());
        Location loc = getloc.getLocation();
        if(loc != null)
        {
            city = getloc.TranslateLocation(loc) + " - " + Utils.GetTimeNow();
            LocationManager manager = (LocationManager)fragment                 //Desligar o sinal do GPS após terminar a utilização
                    .getActivity()
                    .getSystemService(Context.LOCATION_SERVICE);
            manager.removeUpdates(getloc);
        }
        else{
            city = "Unknown" + " - " + Utils.GetTimeNow();                  // Caso não tenha sinal de GPS, assimilar o valor Unknown com o horario local
        }

        return city;
    }

    @Override
    public void CheckCameraDevice()
    {

        PackageManager packMg = captureQrFragment.GetPackageManager();

        if(packMg.hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            captureQrFragment.CheckCameraPermission();
        }
    }

    @Override
    public void CheckInternet(String route)
    {
        boolean conection = Utils.GetNetworkInfo(captureQrFragment) != null;

        if(conection)
        {
            captureQrFragment.CallService(route);
        }
        else
        {
            captureQrFragment.NotHaveInternet();
            captureQrFragment.Refresh();
        }
    }


    public String GetRouteIndexCharacters(String str)
    {
        boolean verify = str.contains(Utils.ROUTE_CHARACTER);
        String index;

        try{
            if(verify)
            {
                String[] arr = str.split("");
                StringBuilder builder = new StringBuilder();

                for(int i = Utils.ROUTE_CHARACTER.length()+1; i < str.length(); i++)
                {
                    builder.append(arr[i]);
                }

                index = builder.toString();
            }
            else
            {
                index = null;
            }

        }
        catch (Exception ex)
        {
            return  null;
        }

        return index;
    }

}
