package day6.fullbang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import day6.fullbang.domain.Product;
import day6.fullbang.service.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MarketPriceController {

    private final ProductService productService;

    @GetMapping("/price/{id}")
    public @ResponseBody
    String getPriceByProductId(@PathVariable(name = "id") Long id) {

        Product product = productService.findOne(id);

        return product.toPriceDto().getJson();
    }
}
