package fleet.dork.btb.hu.fleet.ui.carlist;

import java.util.List;

import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.ui.Screen;

public interface CarListScreen extends Screen{
    void refreshCars(List<Car> cars);
}