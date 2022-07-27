package day6.fullbang.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import day6.fullbang.domain.Product;
import day6.fullbang.service.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MarketPriceController {

    private final ProductService productService;

    @GetMapping("/price/{id}")
    @ResponseBody
    public PriceDto getPriceByProductId(@RequestParam(name = "id") Long id) {

        Product product = productService.findOne(id);

        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(product.getPrice());

        return priceDto;
    }
}
