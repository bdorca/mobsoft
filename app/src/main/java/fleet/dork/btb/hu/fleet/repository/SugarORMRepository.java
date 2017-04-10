package fleet.dork.btb.hu.fleet.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.model.Coordinate;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

public class SugarORMRepository implements Repository {
    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<Car> getAllCars() {
        return SugarRecord.listAll(Car.class);
    }

    @Override
    public void saveCar(Car car) {
        SugarRecord.saveInTx(car.getLocation());
        SugarRecord.saveInTx(car);
    }

    @Override
    public void updateCars(List<Car> cars) {

    }

    @Override
    public void removeCar(Car car) {
        SugarRecord.delete(car.getLocation());
        SugarRecord.delete(car);
    }

    @Override
    public List<Car> getCarsByCoord(Coordinate coord) {
        return SugarRecord.listAll(Car.class);
    }
}
