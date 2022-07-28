package day6.fullbang.dto.response;

import java.time.LocalDate;

import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PriceInfoDto {
    private final String placeName;
    private final String roomName;
    private final Long price;
    private final LocalDate date;

    public String getJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("place_name", placeName);
        jsonObject.addProperty("room_name", roomName);
        jsonObject.addProperty("price", price);
        jsonObject.addProperty("date", String.valueOf(date));

        return jsonObject.toString();
    }
}
