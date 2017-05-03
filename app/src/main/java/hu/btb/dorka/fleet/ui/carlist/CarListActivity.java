package hu.btb.dorka.fleet.ui.carlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.btb.dorka.fleet.R;
import hu.btb.dorka.fleet.model.Car;

import static hu.btb.dorka.fleet.FleetApplication.injector;

public class CarListActivity extends AppCompatActivity implements CarListScreen {

    @Inject
    CarListPresenter carListPresenter;

    private CarListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carlist);
        injector.inject(this);
        adapter=new CarListAdapter(new ArrayList<Car>(),this);
        carListPresenter.refreshList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        carListPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        carListPresenter.detachScreen();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshCars(List<Car> cars) {
        adapter.refreshCars(cars);
    }
}