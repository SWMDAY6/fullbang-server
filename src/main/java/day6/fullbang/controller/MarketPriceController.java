package day6.fullbang.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import day6.fullbang.domain.Product;
import day6.fullbang.dto.response.PriceInfoDto;
import day6.fullbang.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MarketPriceController {

    private final ProductService productService;

    @GetMapping("/price/{id}")
    @ResponseBody
    public PriceInfoDto getPriceByProductId(@PathVariable(name = "id") Long id) {

        Product product = productService.findOne(id);

        return product.toPriceInfoDto();
    }

    @GetMapping("/price")
    @ResponseBody
    public List<PriceInfoDto> getPriceByPlaceName(@RequestParam(name = "place_name") String placeName, @RequestParam String date) {

        List<Product> products = productService.findByPlaceName(placeName, date);
        List<PriceInfoDto> priceInfos = new ArrayList<>();

        for (Product product : products) {
            priceInfos.add(product.toPriceInfoDto());
        }

        return priceInfos;
    }
}
