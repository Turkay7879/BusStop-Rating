package com.example.hw1;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.hw1.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final float MAP_ZOOM = 16.0f;

    private BusStop[] busStops;
    private final List<LatLng> busStopCoordinates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillBusStopList();

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (int i = 0; i < busStops.length; i++) {
            LatLng coord = new LatLng(busStops[i].getLatitude(), busStops[i].getLongitude());
            String markerTitle = busStops[i].getName() + ", " + busStops[i].getPlusCode();
            String markerTag = busStops[i].getName() + "," + busStops[i].getPlusCode();

            busStopCoordinates.add(coord);
            Marker marker = mMap.addMarker(new MarkerOptions().position(coord).title(markerTitle));
            marker.setTag(markerTag);
        }

        // Connect bus stops with polyline
        mMap.addPolyline(new PolylineOptions().addAll(busStopCoordinates));

        // Initial map coordinates for Google Maps camera to focus on
        LatLng initialZoomCoord = new LatLng(busStops[0].getLatitude(), busStops[0].getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(initialZoomCoord)
                .zoom(MAP_ZOOM)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        Intent surveyIntent = new Intent(MapsActivity.this, MainActivity.class);
        surveyIntent.putExtra("busStopInfo", marker.getTag().toString());
        startActivity(surveyIntent);
    }

    private void fillBusStopList() {
        busStops = new BusStop[11];
        busStops[0] = new BusStop("Kampüs Çıkış", 38.36905, 27.21466, "9697+HV");
        busStops[1] = new BusStop("Yerleşke Son Durak", 38.36865, 27.21081, "9696+F8");
        busStops[2] = new BusStop("Mühendislik Fakültesi", 38.36963, 27.20790, "9695+R5");
        busStops[3] = new BusStop("Mimarlık Fakültesi", 38.36851, 27.20658, "9694+9J");
        busStops[4] = new BusStop("Teknopark", 38.36752, 27.20547, "9684+W6");
        busStops[5] = new BusStop("Fen Edebiyat", 38.36814, 27.20281, "9693+54");
        busStops[6] = new BusStop("Sosyal Tesisler", 38.36998, 27.20543, "9694+X6");
        busStops[7] = new BusStop("Kütüphane", 38.37135, 27.20292, "96C3+F4");
        busStops[8] = new BusStop("Spor Salonu", 38.37236, 27.19968, "95CX+VV");
        busStops[9] = new BusStop("Yetmiş Beşinci Yıl İlköğretim Okulu", 38.37377, 27.19672, "95FW+FM");
        busStops[10] = new BusStop("Kampüs Giriş", 38.37580, 27.19425, "95GV+6M");
    }
}