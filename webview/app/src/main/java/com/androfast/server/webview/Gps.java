package com.androfast.server.webview;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class Gps extends Activity {
    private WebView _webView;
    private String latitude;
    private String longitude ;
    private LocationManager locManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        localizacion();
    }

    private void localizacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        locManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location loc = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Log.d("Localizacion", "localizacion: " +  loc.getLatitude());
        latitude = String.valueOf(loc.getLatitude());
        longitude = String.valueOf(loc.getLongitude());

        System.out.println(String.valueOf(loc.getLatitude()));
        Log.d("lob", longitude);
        Log.e("lat", latitude);
        _webView = (WebView) findViewById(R.id.webview);
        _webView.getSettings().setJavaScriptEnabled(true);
        _webView.getSettings().setGeolocationEnabled(true);

        _webView.loadUrl("http://www.google.com/maps?q="+latitude+","+longitude);
    }
}
