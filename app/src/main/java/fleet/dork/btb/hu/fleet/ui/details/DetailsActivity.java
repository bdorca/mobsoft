package fleet.dork.btb.hu.fleet.ui.details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import fleet.dork.btb.hu.fleet.R;
import fleet.dork.btb.hu.fleet.model.Car;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {

    @Inject
    DetailsPresenter detailsPresenter;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        injector.inject(this);
        ButterKnife.bind(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }



    @Override
    public void setDetails(Car details) {

    }

    @Override
    public void commandResponse(int a){}

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
