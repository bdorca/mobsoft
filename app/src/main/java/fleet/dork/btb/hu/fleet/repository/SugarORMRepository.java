package fleet.dork.btb.hu.fleet.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.model.Coordinate;

import static com.orm.SugarRecord.find;

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
        for (Car c : cars) {
            boolean saved = false;
            for (Car x : getAllCars()) {
                if (c.getCarId() == x.getCarId()) {
                    x.setStatus(c.getStatus());
                    x.setGasStatus(c.getGasStatus());
                    x.setLocation(c.getLocation());
                    SugarRecord.saveInTx(x.getLocation());
                    SugarRecord.saveInTx(x);
                    saved = true;
                    break;
                }
            }
            if (!saved) {
                SugarRecord.saveInTx(c.getLocation());
                SugarRecord.saveInTx(c);
            }
        }
    }

    @Override
    public void removeCar(Car car) {
        SugarRecord.delete(car.getLocation());
        SugarRecord.delete(car);
    }

    @Override
    public List<Car> getCarsByCoord(Coordinate coord, float radius) {
        List<Coordinate> coords = SugarRecord.find(Coordinate.class, "latitude<= ? and latitude >= ? and longitude<= ? and longitude >= ?",
                new String[]{String.valueOf(coord.getLatitude() + radius), String.valueOf(coord.getLatitude() - radius),
                        String.valueOf(coord.getLongitude() + radius), String.valueOf(coord.getLongitude() - radius)});
        List<Car> cars = new ArrayList<>();

        for (Coordinate c : coords) {
            cars.addAll(c.getCars());
        }

        return cars;
    }

    @Override
    public Car getCar(int id) {
        return find(Car.class, "carId = ?", String.valueOf(id)).get(0);
    }
}
