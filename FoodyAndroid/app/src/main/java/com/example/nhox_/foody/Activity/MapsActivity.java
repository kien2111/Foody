package com.example.nhox_.foody.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.os.Handler;
import android.os.Message;

import com.example.nhox_.foody.R;
import com.example.nhox_.foody.Service.AppLocationService;
import com.example.nhox_.foody.Service.LocationAddress;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements  GoogleMap.OnMapClickListener, OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    Button back_button;
    Button chon_btn;
    Context context;
    AppLocationService appLocationService;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    ///////////////////
    // input :
    // purpose : Xác định các view trong layout
    // output :
    /////////////////////
    private void Identify() {
        back_button = (Button) findViewById(R.id.back_button);
        chon_btn = (Button) findViewById(R.id.chon_btn);


        chon_btn.setOnClickListener(this);
        back_button.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        context = this;
        appLocationService = new AppLocationService(
                MapsActivity.this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Identify();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }







    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    ///////////////////
    // input :
    // purpose : Xử lý khi map đã sẵn sàng
    // output :
    /////////////////////
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMaxZoomPreference(100);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(10.851035,106.772001),15.0f),100,null);
        mMap.setOnMapClickListener(this);
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                LinearLayout info = new LinearLayout(context);
                info.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
                info.setOrientation(LinearLayout.VERTICAL);
                info.setBackground(context.getResources().getDrawable(R.drawable.border_snippet_mapactivity));
                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                int dimen = (int)getResources().getDimension(R.dimen.dimen_padding_snippet);
                title.setPadding(dimen,dimen,dimen,dimen);
                title.setGravity(Gravity.CENTER);
                title.setText(marker.getTitle());
                title.setTextSize(getResources().getDimension(R.dimen.text_size_small));
                info.addView(title);
                return info;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
    ///////////////////
    // input :
    // purpose : Xử lí khi người dùng click lên view
    // output :
    /////////////////////
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.chon_btn:
                chon_btn_HandleClick();
                break;
            default:
                break;
        }
    }
    ///////////////////
    // input :
    // purpose : Lấy các giá trị latgeo và longgeo trả về cho activity trước
    // output :
    /////////////////////
    public void chon_btn_HandleClick() {
        Intent intent = new Intent();
        intent.putExtra("lat",latgeo);
        intent.putExtra("long",longgeo);
        setResult(RESULT_OK,intent);
        finish();
    }
    public double latgeo;
    public double longgeo;
    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng click lên vị trí map
    // output :
    /////////////////////
    @Override
    public void onMapClick(LatLng latLng) {

        mMap.clear();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15.0f),100,null);
        latgeo = latLng.latitude;
        longgeo = latLng.longitude;
        LocationAddress locationAdd = new LocationAddress();
        locationAdd.getAddressFromLocation(latgeo, longgeo,
                getApplicationContext(), new GeocoderHandler());

       Marker marker =mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.guiderpopup)).position(latLng)
                .title(locationAddress));
         marker.showInfoWindow();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public static String locationAddress;
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
        }
    }



}
