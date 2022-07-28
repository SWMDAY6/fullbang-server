package day6.fullbang.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import day6.fullbang.domain.Product;
import day6.fullbang.service.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MarketPriceController {

    private final ProductService productService;

    @GetMapping("/price/{id}")
    @ResponseBody
    public String getPriceByProductId(@PathVariable(name = "id") Long id) {

        Product product = productService.findOne(id);

        return product.toPriceInfoDto().getJson();
    }

    @GetMapping("/price")
    @ResponseBody
    public String getPriceByPlaceName(@RequestParam(name = "place_name") String placeName, @RequestParam String date) {

        List<Product> products = productService.findByPlaceName(placeName, date);

        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        for (Product product : products) {
            jsonArray.add(product.toPriceInfoDto().getJson());
        }

        jsonObject.add("priceInfos", jsonArray);

        return jsonObject.toString();
    }
}
