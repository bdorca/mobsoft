package fleet.dork.btb.hu.fleet.model;

import com.orm.dsl.Table;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

@Table
public class Car {
    private Long id;
    private int carId;
    private String licence;
    private String type;
    private Coordinate location;
    private int gasStatus;
    private Status status;

    public Car() {
    }

    public Car(String licence, String type, Coordinate location) {
        this.licence = licence;
        this.type = type;
        this.location = location;
    }

    public Car(int carId, String licence, String type, Coordinate location, int gasStatus, Status status) {
        this.carId = carId;
        this.licence = licence;
        this.type = type;
        this.location = location;
        this.gasStatus = gasStatus;
        this.status = status;
    }

    public Car(Long id, int carId, String licence, String type, Coordinate location, int gasStatus, Status status) {
        this.id=id;
        this.carId = carId;
        this.licence = licence;
        this.type = type;
        this.location = location;
        this.gasStatus = gasStatus;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Coordinate getLocation() {
        return location;
    }

    public void setLocation(Coordinate location) {
        this.location = location;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getGasStatus() {
        return gasStatus;
    }

    public void setGasStatus(int gasStatus) {
        this.gasStatus = gasStatus;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        STOPPED, RUNNING, TURNED_OFF
    }
}
