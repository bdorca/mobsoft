package hu.btb.dorka.fleet.ui.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.btb.dorka.fleet.R;
import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.model.Coordinate;

import static hu.btb.dorka.fleet.FleetApplication.injector;

public class MapActivity extends AppCompatActivity implements MapScreen {

    public static final String POSITION = "POSITION";

    @Inject
    MapPresenter mapPresenter;

    @Bind(R.id.mapView)
    MapView mapView;

    Coordinate startingPos;

    List<Marker> markers = new ArrayList<>();

    List<Car> cars = new ArrayList<>();
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        injector.inject(this);
        ButterKnife.bind(this);
        startingPos = Parcels.unwrap(getIntent().getParcelableExtra(POSITION));

        mapView.onCreate(savedInstanceState);

        mapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(this.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(setCameraCallback);
    }

    OnMapReadyCallback setCameraCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            CameraUpdate center=
                    CameraUpdateFactory.newLatLng(new LatLng(startingPos.getLatitude(),
                            startingPos.getLongitude()));
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(5);

            mMap.moveCamera(center);
            mMap.animateCamera(zoom);
            refresh();
            mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                @Override
                public void onCameraMove() {
                    refresh();
                }
            });
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        mapPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapPresenter.detachScreen();
    }

    private void refresh() {
        CameraPosition cp = mMap.getCameraPosition();
        mapPresenter.refreshMap(new Coordinate((float) (cp.target.latitude), (float) (cp.target.longitude)), cp.zoom * 10);

    }

    private void setCars(List<Car> cars) {
        for (Marker m : markers) {
            m.remove();
        }
        Log.e("CARS", cars.size()>0?cars.get(0).toString():"NULL");
        for (Car c : cars) {
            Marker m = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(c.getLocation().getLatitude(), c.getLocation().getLongitude()))
                    .title(c.getLicence()));
            markers.add(m);
        }
    }


    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshCoordinates(List<Car> list) {
        cars = list;
        setCars(cars);
    }
}
