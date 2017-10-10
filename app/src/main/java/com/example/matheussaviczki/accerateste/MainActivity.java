package com.example.matheussaviczki.accerateste;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;


import com.example.matheussaviczki.accerateste.layout.CaptureQrFragment;
import com.example.matheussaviczki.accerateste.layout.ListFragment;
import com.example.matheussaviczki.accerateste.models.PersonagemModel;
import com.example.matheussaviczki.accerateste.models.RealmConfig;
import com.example.matheussaviczki.accerateste.models.WebServiceConsum;
import com.example.matheussaviczki.accerateste.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ListFragment listFragment;
    private CaptureQrFragment captureQrFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        RealmConfig realmConfig = new RealmConfig(getApplicationContext());
        realmConfig.InitializeRealm();
        realmConfig.ListCharacters();
    }

    private void init()
    {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        ListFragment listFragment = new ListFragment();
        CaptureQrFragment captureQrFragment = new CaptureQrFragment();
        mViewPager = (ViewPager) findViewById(R.id.container);
        adaptViewPager(mViewPager, listFragment, captureQrFragment);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFC300"));
    }

    private void adaptViewPager(ViewPager viewPager, ListFragment listFragment, CaptureQrFragment captureQrFragment) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(listFragment, "Lista");
        adapter.addFragment(captureQrFragment, "Capturar");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
