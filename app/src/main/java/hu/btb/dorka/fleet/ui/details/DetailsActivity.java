package hu.btb.dorka.fleet.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.btb.dorka.fleet.R;
import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.ui.map.MapActivity;
import hu.btb.dorka.fleet.util.UIUtils;

import static hu.btb.dorka.fleet.FleetApplication.injector;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {

    @Inject
    public DetailsPresenter detailsPresenter;

    @Bind(R.id.fab)
    public FloatingActionButton fab;

    @Bind(R.id.plateTextView)
    public TextView plateTextView;

    @Bind(R.id.typeTextView)
    public TextView typeTextView;

    @Bind(R.id.gasStatusBar)
    public ProgressBar gasStatusBar;

    @Bind(R.id.statusImageView)
    public ImageView statusImageView;


    private Car details;

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
        detailsPresenter.refreshData();
    }

    @OnClick(R.id.toMapButton)
    public void toMap(){
        Intent i=new Intent(this, MapActivity.class);
        i.putExtra("latitude",details.getLocation().getLatitude());
        i.putExtra("longitude",details.getLocation().getLongitude());
        startActivity(i);
    }

    @Override
    public void setDetails(Car details) {
        this.details=details;
        plateTextView.setText(details.getLicence());
        typeTextView.setText(details.getType());
        gasStatusBar.setProgress(details.getGasStatus());
        UIUtils.setStatusImageView(statusImageView,details.getStatus());
    }

    @Override
    public void commandResponse(int a) {
        Toast.makeText(this,"Command response: "+a,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
