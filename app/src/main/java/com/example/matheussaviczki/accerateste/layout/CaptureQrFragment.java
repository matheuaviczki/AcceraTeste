package com.example.matheussaviczki.accerateste.layout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.matheussaviczki.accerateste.MainActivity;
import com.example.matheussaviczki.accerateste.R;
import com.example.matheussaviczki.accerateste.SelectThemeActivity;
import com.example.matheussaviczki.accerateste.models.PersonagemModel;
import com.example.matheussaviczki.accerateste.models.WebServiceConsum;
import com.example.matheussaviczki.accerateste.presenter.PresenterCaptureQR;
import com.example.matheussaviczki.accerateste.tasks.CaptureQR;
import com.example.matheussaviczki.accerateste.tasks.WebService;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Matheus Saviczki on 04/10/2017.
 */

public class CaptureQrFragment extends Fragment implements CaptureQR.View, ZXingScannerView.ResultHandler {

    private CaptureQR.Presenter presenterCaptureQR;
    private ZXingScannerView zxingReader;                       //Dependencia importada para leitura de códigos QR
    private final int CAMERA_CODE = 10;
    private boolean haveCameraPermission;
    private SharedPreferences sharedP;
    private SharedPreferences.Editor savePermission;            //Shared preferences criado para salvar um valor boolean para que não sejá necessário a verificação continua de permissão de camera

    public CaptureQrFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Init();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_qrcapture, container, false);
        RelativeLayout frame_qr_capture = (RelativeLayout) rootView.findViewById(R.id.frame_qr_capture);

        frame_qr_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterCaptureQR.CheckCameraDevice();
                if(haveCameraPermission)
                {
                    ScanQRCode();
                }
            }
        });

        return rootView;
    }

    private void Init()
    {
        sharedP = getActivity().getApplicationContext().getSharedPreferences("savePermission", Context.MODE_PRIVATE);   //Crio a variavel de ambiente antes de chamar a camera
        savePermission = sharedP.edit();
        presenterCaptureQR = new PresenterCaptureQR(this);
    }

    @Override
    public void CheckCameraPermission()
    {
        haveCameraPermission = sharedP.getBoolean("savePermission", false);                 //Verificação se já possui permissão de camera

        if(!haveCameraPermission)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(getActivity().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                {
                    CallCamera();
                }
                else
                {
                    String[] needPermission = {Manifest.permission.CAMERA};
                    requestPermissions(needPermission, CAMERA_CODE);
                }
            }
        }
    }

    private void CallCamera()
    {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.camera_permission), Toast.LENGTH_SHORT).show();
    }

    private void ScanQRCode()
    {
        zxingReader = new ZXingScannerView(getActivity().getApplicationContext());
        getActivity().setContentView(zxingReader);
        zxingReader.setResultHandler(this);
        zxingReader.startCamera();
        zxingReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zxingReader.stopCamera();
                zxingReader.removeAllViews();
                Refresh();
            }
        });
    }


    @Override
    public void NotHaveCameraDevice()
    {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.camera_device), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void NotHaveInternet()
    {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.internet_verification) , Toast.LENGTH_LONG).show();
    }

    @Override
    public PackageManager GetPackageManager()
    {
        return getActivity().getApplicationContext().getPackageManager();
    }

    @Override
    public ConnectivityManager GetConnectivityManager()
    {
        return (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public void Refresh()
    {
        getActivity().finish();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == CAMERA_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                savePermission.putBoolean("savePermission", true);       //Ganhei permissão de camera, então salvo em uma variavel de ambiente como valor true
                savePermission.apply();
                CallCamera();
                haveCameraPermission = true;
            }
        }
    }

    @Override
    public void CallService(String route)
    {
        String cityhour = "";
        cityhour = presenterCaptureQR.GetCityHour(this);
        WebServiceConsum.GetPersonagemModel(route, this, cityhour);
    }

    @Override
    public void handleResult(Result result) {                       // Pega o resultado da leitura do scanner
        Toast.makeText(getActivity().getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();

        String getURL = presenterCaptureQR.GetRouteIndexCharacters(result.toString());

        if(getURL != null)
        {
            zxingReader.setClickable(false);
            presenterCaptureQR.CheckInternet(getURL);
            zxingReader.stopCamera();
            zxingReader.removeView(zxingReader);
        }
        else
        {
            zxingReader.resumeCameraPreview(this);
        }

    }


}
