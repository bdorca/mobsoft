package fleet.dork.btb.hu.fleet.repository;

import android.content.Context;

import java.util.List;

import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.model.Coordinate;

public interface Repository {

    void open(Context context);

    void close();

    List<Car> getAllCars();

    void saveCar(Car car);

    void updateCars(List<Car> cars);

    void removeCar(Car car);

    List<Car> getCarsByCoord(Coordinate coord);

}