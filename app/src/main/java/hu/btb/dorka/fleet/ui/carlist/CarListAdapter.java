package hu.btb.dorka.fleet.ui.carlist;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hu.btb.dorka.fleet.R;
import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.util.UIUtils;


/**
 * Created by dorka on 2017.04.25..
 */

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarViewHolder> {

    private List<Car> cars;
    private Context ctx;


    public CarListAdapter(List<Car> cars, Context ctx) {
        this.cars = cars;
        this.ctx = ctx;
    }


    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_carlist_element_row, viewGroup, false);
        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarViewHolder carViewHolder, int position) {
        carViewHolder.licencePlate.setText(cars.get(position).getLicence());
        carViewHolder.setStatus(cars.get(position).getStatus());
    }


    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {

        public TextView licencePlate;
        public ImageView statusImageView;
        CardView cardView;

        public CarViewHolder(View itemView) {
            super(itemView);
            licencePlate = (TextView) itemView.findViewById(R.id.licenceViewHolderTextView);
            statusImageView = (ImageView) itemView.findViewById(R.id.statusViewHolderImageView);
            cardView=(CardView) itemView.findViewById(R.id.carCardView);
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
