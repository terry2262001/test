package vn.edu.stu.demomapbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.EdgeInsets;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
    }

    private void addControls() {
        mapView = findViewById(R.id.mapView);
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
        // TDTU: 10.7327188719329, 106.699188819671
        Point pointTDTU = Point.fromLngLat(106.699188819671, 10.7327188719329);
        // HMCUSSH: 10.786015602203218, 106.70279179708808
        Point pointHCMUSSH = Point.fromLngLat(106.70279179708808, 10.786015602203218);

        AnnotationPlugin plugin = AnnotationPluginImplKt.getAnnotations(mapView);
        PointAnnotationManager manager = PointAnnotationManagerKt.createPointAnnotationManager(
                plugin,
                new AnnotationConfig()
        );
        PointAnnotationOptions optionsSTU = new PointAnnotationOptions()
                .withPoint(pointSTU)
                .withTextField("STU nè")
                .withIconImage(BitmapFactory.decodeResource(this.getResources(), R.drawable.red_marker));
        manager.create(optionsSTU);
        PointAnnotationOptions optionsTDTU = new PointAnnotationOptions()
                .withPoint(pointTDTU)
                .withTextField("TDTU nè")
                .withIconImage(BitmapFactory.decodeResource(this.getResources(), R.drawable.red_marker));
        manager.create(optionsTDTU);
        PointAnnotationOptions optionsHCMUSSH = new PointAnnotationOptions()
                .withPoint(pointHCMUSSH)
                .withTextField("HCMUSSH nè")
                .withIconImage(BitmapFactory.decodeResource(this.getResources(), R.drawable.red_marker));
        manager.create(optionsHCMUSSH);

//        CameraOptions cameraOptions = new CameraOptions.Builder()
//                .center(pointSTU)
//                .zoom(16.0)
//                .bearing(0.0)
//                .pitch(0.0)
//                .build();

        List<Point> points = new ArrayList<>();
        points.add(pointSTU);
        points.add(pointTDTU);
        points.add(pointHCMUSSH);
        List<List<Point>> coordinates = new ArrayList<>();
        coordinates.add(points);

        Polygon polygon = Polygon.fromLngLats(coordinates);
        EdgeInsets edgeInsets = new EdgeInsets(100.0, 100.0, 100.0, 100.0);
        CameraOptions cameraOptions = mapView.getMapboxMap().cameraForGeometry(
                polygon,
                edgeInsets,
                0.0,
                0.0
        );

        mapView.getMapboxMap().setCamera(cameraOptions);
    }
}