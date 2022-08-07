package day6.fullbang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import day6.fullbang.dto.product.PriceInfoDto;
import day6.fullbang.dto.request.MarketPriceConditionDto;
import day6.fullbang.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<PriceInfoDto> getPriceInfoByAddressCode(MarketPriceConditionDto marketPriceConditionDto,
        String addressCodeHead) {
        return productRepository.getPriceInfoByAddressCode(marketPriceConditionDto, addressCodeHead);
    }
}
