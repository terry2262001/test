package com.example.nguyentruongtho_dh51900920.Fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyentruongtho_dh51900920.AboutActivity;
import com.example.nguyentruongtho_dh51900920.R;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;


public class MapsFragment extends Fragment {
    MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.fragment_maps, container, false);
        addControls(view);
        return view;
    }
    private void addControls(View view) {
        mapView = view.findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(
                Style.SATELLITE,
                new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        thietLapBanDo();
                    }
                }
        );
    }
    private void thietLapBanDo() {
        // STU: 10.738102290467015, 106.67772674813946
        Point pointSTU = Point.fromLngLat(106.67772674813946, 10.738102290467015);

        AnnotationPlugin plugin = AnnotationPluginImplKt.getAnnotations(mapView);
        PointAnnotationManager manager = PointAnnotationManagerKt.createPointAnnotationManager(
                plugin,
                new AnnotationConfig()
        );
        PointAnnotationOptions optionsSTU = new PointAnnotationOptions()
                .withPoint(pointSTU)
                .withTextField("Trường Đại Học Công Nghệ Sài Gòn")
                .withIconImage(BitmapFactory.decodeResource(this.getResources(), R.drawable.red_marker));
        manager.create(optionsSTU);


        CameraOptions cameraOptions = new CameraOptions.Builder()
                .center(pointSTU)
                .zoom(16.0)
                .bearing(0.0)
                .pitch(0.0)
                .build();



        mapView.getMapboxMap().setCamera(cameraOptions);
    }



    @Override
    public void onPause() {
        super.onPause();
        Intent intent = new Intent(getActivity(), AboutActivity.class);
        startActivity(intent);
    }
}