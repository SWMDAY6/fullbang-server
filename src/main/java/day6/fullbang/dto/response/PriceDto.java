package day6.fullbang.dto.response;

import java.time.LocalDateTime;

import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PriceDto {
    private Long price;

    public String getJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("price", price);

        return jsonObject.toString();
    }
}
