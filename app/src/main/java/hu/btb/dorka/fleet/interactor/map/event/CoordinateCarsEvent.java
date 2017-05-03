package hu.btb.dorka.fleet.interactor.map.event;

import java.util.List;

import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.model.Coordinate;
import hu.btb.dorka.fleet.util.NetworkEvent;

/**
 * Created by Dorka on 2017.04.22..
 */
public class CoordinateCarsEvent extends NetworkEvent {
    private List<Car> cars;
    private Coordinate coordinate;
    private float radius;

    public CoordinateCarsEvent() {
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
