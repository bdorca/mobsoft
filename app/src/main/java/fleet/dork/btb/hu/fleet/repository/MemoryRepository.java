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
        Car car1 = new Car(Long.valueOf(1),1,"abc-123", "toyota", new Coordinate(45f, 15f),100,Car.StatusEnum.RUNNING);
        Car car2 = new Car(Long.valueOf(2),2,"def-456", "bmw", new Coordinate(45f, 15.5f),50,Car.StatusEnum.STOPPED);
        Car car3 = new Car(Long.valueOf(3),3,"ghi-789", "skoda", new Coordinate(10f, 15.5f),75, Car.StatusEnum.TURNED_OFF);
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
    public List<Car> getCarsByCoord(Coordinate coord, float radius) {
        List<Car> ret=new ArrayList<>();
        for(int i=0;i<cars.size();i++){
            if(cars.get(i).getLocation().getLatitude()<=coord.getLatitude()+radius && cars.get(i).getLocation().getLatitude()>=coord.getLatitude()-radius &&
                    cars.get(i).getLocation().getLongitude()<=coord.getLongitude()+radius && cars.get(i).getLocation().getLongitude()>=coord.getLongitude()-radius){
                ret.add(cars.get(i));
            }
        }
        return ret;
    }

    @Override
    public Car getCar(int id) {
        for(int i=0;i<cars.size();i++){
            if(cars.get(i).getCarId()==id){
                return cars.get(i);
            }
        }
        return null;
    }
}
