
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "roomNumber",
    "roomType"
})
public class RoomDetails {

    @JsonProperty("roomNumber")
    private String roomNumber;
    @JsonProperty("roomType")
    private String roomType;

    @JsonProperty("roomNumber")
    public String getRoomNumber() {
        return roomNumber;
    }

    @JsonProperty("roomNumber")
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @JsonProperty("roomType")
    public String getRoomType() {
        return roomType;
    }

    @JsonProperty("roomType")
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

}
