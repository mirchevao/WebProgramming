package web.programming.aud.wpaud.service.impl;

import org.springframework.stereotype.Service;
import web.programming.aud.wpaud.model.Category;
import web.programming.aud.wpaud.model.Manufacturer;
import web.programming.aud.wpaud.model.Product;
import web.programming.aud.wpaud.model.exceptions.CategoryNotFoundException;
import web.programming.aud.wpaud.model.exceptions.ManufacturerNotFoundException;
import web.programming.aud.wpaud.repository.InMemoryCategoryRepository;
import web.programming.aud.wpaud.repository.InMemoryManufacturerRepository;
import web.programming.aud.wpaud.repository.InMemoryProductRepository;
import web.programming.aud.wpaud.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final InMemoryProductRepository productRepository;
    private final InMemoryCategoryRepository categoryRepository;
    private final InMemoryManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(InMemoryProductRepository productRepository, InMemoryCategoryRepository categoryRepository, InMemoryManufacturerRepository manufacturerRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;

    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        return productRepository.save(name, price, quantity, category, manufacturer);
    }

    @Override
    public void deleteById(Long id) {
       this.productRepository.deleteById(id);
    }
}
