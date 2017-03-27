package fleet.dork.btb.hu.fleet.ui.carlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import fleet.dork.btb.hu.fleet.R;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

public class CarListActivity extends AppCompatActivity implements CarListScreen {

    @Inject
    CarListPresenter carListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injector.inject(this);
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
}