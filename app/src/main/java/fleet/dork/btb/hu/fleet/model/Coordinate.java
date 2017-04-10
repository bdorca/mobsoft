package fleet.dork.btb.hu.fleet.model;

import com.orm.dsl.Table;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

@Table
public class Coordinate {
    private Long id;
    private float latitude;
    private float longitude;

    public Coordinate() {
    }

    public Coordinate(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
