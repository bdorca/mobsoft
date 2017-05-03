package hu.btb.dorka.fleet.ui.carlist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.btb.dorka.fleet.R;
import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.util.UIUtils;


/**
 * Created by dorka on 2017.04.25..
 */

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarViewHolder> {

    private List<Car> cars;

    private OnCarClickListener listener;

    public CarListAdapter(List<Car> cars, OnCarClickListener listener) {
        this.cars = cars;
        this.listener=listener;
    }

    public interface OnCarClickListener {
        void onCarClick(Car car);
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_carlist_element_row, viewGroup, false);
        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarViewHolder carViewHolder, final int position) {
        final Car car = cars.get(position);
        carViewHolder.licencePlate.setText(car.getLicence());
        carViewHolder.setStatus(car.getStatus());
        carViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCarClick(car);
            }
        });
    }


    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.licenceViewHolderTextView)
        public TextView licencePlate;
        @Bind(R.id.statusViewHolderImageView)
        public ImageView statusImageView;
        @Bind(R.id.carCardView)
        CardView cardView;

        public CarViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setStatus(Car.StatusEnum status){
            UIUtils.setStatusImageView(statusImageView,status);
        }
    }

    public void refreshCars(List<Car> c){
        cars=c;
        this.notifyDataSetChanged();

    }
}
