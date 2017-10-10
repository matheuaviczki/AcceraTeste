package com.example.matheussaviczki.accerateste.tasks;

import android.content.pm.PackageManager;
import android.net.ConnectivityManager;

import com.example.matheussaviczki.accerateste.layout.CaptureQrFragment;

/**
 * Created by Matheus Saviczki on 06/10/2017.
 */

public interface CaptureQR {

    interface View{

        void CheckCameraPermission();

        void NotHaveCameraDevice();

        void NotHaveInternet();

        PackageManager GetPackageManager();

        ConnectivityManager GetConnectivityManager();

        void Refresh();

        void CallService(String route);
    }

    interface Presenter{

        String GetCityHour(CaptureQrFragment fragment);

        void CheckCameraDevice();

        void CheckInternet(String route);

        String GetRouteIndexCharacters(String str);
    }

}
