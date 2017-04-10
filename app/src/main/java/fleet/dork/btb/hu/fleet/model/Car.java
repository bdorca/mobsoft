package fleet.dork.btb.hu.fleet.model;

import com.orm.dsl.Table;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

@Table
public class Car {
    private Long id;
    private String licence;
    private String type;
    private Coordinate location;

    public Car() {
    }

    public Car(String licence, String type, Coordinate location) {
        this.licence = licence;
        this.type = type;
        this.location = location;
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
}
