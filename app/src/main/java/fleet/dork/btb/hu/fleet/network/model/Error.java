package fleet.dork.btb.hu.fleet.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;




public class Error   {
  
  @SerializedName("code")
  private int code = 0;
  
  @SerializedName("message")
  private String message = null;
  
  @SerializedName("fields")
  private String fields = null;
  

  
  /**
   **/
  public int getCode() {
    return code;
  }
  public void setCode(int code) {
    this.code = code;
  }

  
  /**
   **/
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

  
  /**
   **/
  public String getFields() {
    return fields;
  }
  public void setFields(String fields) {
    this.fields = fields;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(code, error.code) &&
        Objects.equals(message, error.message) &&
        Objects.equals(fields, error.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, fields);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
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
