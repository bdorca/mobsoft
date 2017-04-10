package fleet.dork.btb.hu.fleet.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.model.Coordinate;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

public class MemoryRepository implements Repository {

    private static final long MINUTE = 60 * 1000;

    public static List<Car> cars;

    @Override
    public void open(Context context) {
        Car car1 = new Car("abc-123", "toyota", new Coordinate(45, 15));
        Car car2 = new Car("def-456", "bmw", new Coordinate(45, 15.5f));
        Car car3 = new Car("ghi-789", "skoda", new Coordinate(10, 15.5f));
        cars=new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
    }

    @Override
    public void close() {

    }

    @Override
    public List<Car> getAllCars() {
        return cars;
    }

    @Override
    public void saveCar(Car car) {
        cars.add(car);
    }

    @Override
    public void updateCars(List<Car> cars) {
        for (Car d : cars) {
            boolean isIn=false;
            for (Car c : MemoryRepository.cars) {
                if(d.getLicence().equalsIgnoreCase(c.getLicence())){
                    isIn=true;
                    break;
                }
            }
            if(!isIn){
                cars.add(d);
            }
        }
    }

    @Override
    public void removeCar(Car car) {
        cars.remove(car);
    }

    @Override
    public List<Car> getCarsByCoord(Coordinate coord) {
        List<Car> ret=new ArrayList<>();
        for(int i=0;i<cars.size();i++){
            if(coord.getLatitude()==cars.get(i).getLocation().getLatitude()){
                ret.add(cars.get(i));
            }
        }
        return ret;
    }
}
