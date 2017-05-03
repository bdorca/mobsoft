package hu.btb.dorka.fleet.ui.carlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.btb.dorka.fleet.R;
import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.ui.details.DetailsActivity;

import static hu.btb.dorka.fleet.FleetApplication.injector;

public class CarListActivity extends AppCompatActivity implements CarListScreen {

    @Inject
    CarListPresenter carListPresenter;

    private CarListAdapter adapter;

    @Bind(R.id.carListView)
    public RecyclerView carsRecyclerView;

    @Bind(R.id.carListContainer)
    public SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carlist);
        injector.inject(this);
        ButterKnife.bind(this);

        carsRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        carsRecyclerView.setLayoutManager(llm);


        adapter=new CarListAdapter(new ArrayList<Car>(), new CarListAdapter.OnCarClickListener() {
            @Override
            public void onCarClick(Car car) {
                navigateToDetails(car);
            }
        });
        carsRecyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                carListPresenter.refreshList();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carListPresenter.attachScreen(this);
        carListPresenter.refreshList();

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
        refreshLayout.setRefreshing(false);
    }

    public void navigateToDetails(Car car){
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.CAR, Parcels.wrap(car));
        startActivity(intent);
    }
}