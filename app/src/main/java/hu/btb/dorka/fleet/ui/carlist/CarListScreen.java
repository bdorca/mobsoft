package hu.btb.dorka.fleet.ui.carlist;

import java.util.List;

import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.ui.Screen;

public interface CarListScreen extends Screen{
    void refreshCars(List<Car> cars);
}