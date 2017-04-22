package fleet.dork.btb.hu.fleet.ui.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import fleet.dork.btb.hu.fleet.R;
import fleet.dork.btb.hu.fleet.model.Car;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

public class MapActivity extends AppCompatActivity implements MapScreen {

    @Inject
    MapPresenter mapPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        injector.inject(this);
    }


    @Override
    public void refreshCoordinates(List<Car> list) {

    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
