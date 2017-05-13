package hu.btb.dorka.fleet.model;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import org.parceler.Parcel;

import java.util.List;
import java.util.Objects;


@Table
@Parcel
public class Coordinate   {

    protected Long id;

    @SerializedName("latitude")
    protected float latitude = 0;

    @SerializedName("longitude")
    protected float longitude = 0;

    public Coordinate() {
    }

    public Coordinate(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * latitude
     **/
    public float getLatitude() {
        return latitude;
    }
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }


    /**
     * longitude
     **/
    public float getLongitude() {
        return longitude;
    }
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate coordinate = (Coordinate) o;
        return Objects.equals(latitude, coordinate.latitude) &&
                Objects.equals(longitude, coordinate.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Coordinate {\n");

        sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
        sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public List<Car> getCars() {
        return SugarRecord.find(Car.class, "coordinate = ?", String.valueOf(id));
    }

}
