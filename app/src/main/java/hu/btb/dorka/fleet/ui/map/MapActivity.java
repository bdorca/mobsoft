package hu.btb.dorka.fleet.ui.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import hu.btb.dorka.fleet.R;
import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.model.Coordinate;

import static hu.btb.dorka.fleet.FleetApplication.injector;

public class MapActivity extends AppCompatActivity implements MapScreen {

    @Inject
    MapPresenter mapPresenter;

    MapFragment mapFragment;

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
        mapFragment= (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        startingPos = new Coordinate(getIntent().getFloatExtra("latitude", 47.49f)
                , getIntent().getFloatExtra("longitude", 19.05f));
        mapFragment.getMapAsync(setCameraCallback);
    }

    OnMapReadyCallback setCameraCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(startingPos.getLatitude() - 1, startingPos.getLongitude() - 1)
                    , new LatLng(startingPos.getLatitude() + 1, startingPos.getLongitude() + 1)));
            refresh();
            mMap.setOnCameraMoveCanceledListener(new GoogleMap.OnCameraMoveCanceledListener() {
                @Override
                public void onCameraMoveCanceled() {
                    refresh();
                }
            });
        }
    };

    private void refresh(){
        CameraPosition cp=mMap.getCameraPosition();
        mapPresenter.refreshMap(new Coordinate((float)(cp.target.latitude),(float)(cp.target.longitude)),cp.zoom*10);

    }

    private void setCars(){
            for (Marker m : markers) {
                m.remove();
            }
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
        setCars();
    }
}
