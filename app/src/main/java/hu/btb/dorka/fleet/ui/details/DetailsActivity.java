package hu.btb.dorka.fleet.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.btb.dorka.fleet.R;
import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.model.Command;
import hu.btb.dorka.fleet.ui.map.MapActivity;
import hu.btb.dorka.fleet.util.UIUtils;

import static hu.btb.dorka.fleet.FleetApplication.injector;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {

    public static final String CAR = "CAR";

    @Inject
    public DetailsPresenter detailsPresenter;

    @Bind(R.id.fabStart)
    public com.github.clans.fab.FloatingActionButton fabStart;
    @Bind(R.id.fabStop)
    public com.github.clans.fab.FloatingActionButton fabStop;
    @Bind(R.id.fabAlert)
    public com.github.clans.fab.FloatingActionButton fabAlert;
    @Bind(R.id.fabUpdate)
    public com.github.clans.fab.FloatingActionButton fabUpdate;

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

        details= Parcels.unwrap(getIntent().getParcelableExtra(CAR));
        fabStart.setOnClickListener(fabListener);
        fabStop.setOnClickListener(fabListener);
        fabAlert.setOnClickListener(fabListener);
        fabUpdate.setOnClickListener(fabListener);
    }

    View.OnClickListener fabListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Command c;
            if(v.equals(fabStart)){
                c=Command.START;
            }else if(v.equals(fabStop)){
                c=Command.STOP;
            }else if(v.equals(fabAlert)){
                c=Command.ALERT;
            }else{
                c=Command.UPDATE;
            }
            detailsPresenter.sendCommand(c);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        detailsPresenter.attachScreen(this);
        detailsPresenter.setCurrentCar(details);
        detailsPresenter.refreshData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        detailsPresenter.detachScreen();
    }

    @OnClick(R.id.toMapButton)
    public void toMap(){
        Intent i=new Intent(this, MapActivity.class);
        i.putExtra(MapActivity.POSITION,Parcels.wrap(details.getLocation()));
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
