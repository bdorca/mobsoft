package hu.btb.dorka.fleet.repository;

import android.content.Context;

import java.util.List;

import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.model.Coordinate;

public interface Repository {

    void open(Context context);

    void close();

    List<Car> getAllCars();

    void saveCar(Car car);

    void updateCars(List<Car> cars);

    void removeCar(Car car);

    List<Car> getCarsByCoord(Coordinate coord, float radius);

    Car getCar(int id);
}