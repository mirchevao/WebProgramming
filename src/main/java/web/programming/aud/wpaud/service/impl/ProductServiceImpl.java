package web.programming.aud.wpaud.service.impl;

import org.springframework.stereotype.Service;
import web.programming.aud.wpaud.model.Category;
import web.programming.aud.wpaud.model.Manufacturer;
import web.programming.aud.wpaud.model.Product;
import web.programming.aud.wpaud.model.exceptions.CategoryNotFoundException;
import web.programming.aud.wpaud.model.exceptions.ManufacturerNotFoundException;
import web.programming.aud.wpaud.repository.impl.InMemoryCategoryRepository;
import web.programming.aud.wpaud.repository.impl.InMemoryManufacturerRepository;
import web.programming.aud.wpaud.repository.impl.InMemoryProductRepository;
import web.programming.aud.wpaud.repository.jpa.CategoryRepository;
import web.programming.aud.wpaud.repository.jpa.ManufacturerRepository;
import web.programming.aud.wpaud.repository.jpa.ProductRepository;
import web.programming.aud.wpaud.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {

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
    @Transactional
    public Optional<Product> save(String name,
                                  Double price,
                                  Integer quantity,
                                  Long categoryId,
                                  Long manufacturerId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()
                -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(()
                -> new ManufacturerNotFoundException(manufacturerId));

        this.productRepository.deleteByName(name);

        return Optional.of(productRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
       this.productRepository.deleteById(id);
    }
}
