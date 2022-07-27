package day6.fullbang.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day6.fullbang.domain.Product;
import day6.fullbang.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }
}
