package fleet.dork.btb.hu.fleet.model;

import com.google.gson.annotations.SerializedName;
import com.orm.dsl.Table;

import java.util.Objects;


@Table
public class Car   {

    @SerializedName("carId")
    private Integer carId = null;

    @SerializedName("licence")
    private String licence = null;

    @SerializedName("type")
    private String type = null;

    @SerializedName("gas_status")
    private Integer gasStatus = null;


    public enum StatusEnum {
        @SerializedName("stopped")
        STOPPED("stopped"),

        @SerializedName("running")
        RUNNING("running"),

        @SerializedName("turned_off")
        TURNED_OFF("turned_off");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @SerializedName("status")
    private StatusEnum status = null;

    @SerializedName("location")
    private Coordinate location = null;



    /**
     **/
    public Integer getCarId() {
        return carId;
    }
    public void setCarId(Integer carId) {
        this.carId = carId;
    }


    /**
     * Plate.
     **/
    public String getLicence() {
        return licence;
    }
    public void setLicence(String licence) {
        this.licence = licence;
    }


    /**
     * Car type.
     **/
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    /**
     * percent
     **/
    public Integer getGasStatus() {
        return gasStatus;
    }
    public void setGasStatus(Integer gasStatus) {
        this.gasStatus = gasStatus;
    }


    /**
     **/
    public StatusEnum getStatus() {
        return status;
    }
    public void setStatus(StatusEnum status) {
        this.status = status;
    }


    /**
     **/

    public Coordinate getLocation() {
        return location;
    }
    public void setLocation(Coordinate location) {
        this.location = location;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) &&
                Objects.equals(licence, car.licence) &&
                Objects.equals(type, car.type) &&
                Objects.equals(gasStatus, car.gasStatus) &&
                Objects.equals(status, car.status) &&
                Objects.equals(location, car.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, licence, type, gasStatus, status, location);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Car {\n");

        sb.append("    carId: ").append(toIndentedString(carId)).append("\n");
        sb.append("    licence: ").append(toIndentedString(licence)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    gasStatus: ").append(toIndentedString(gasStatus)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
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
}
