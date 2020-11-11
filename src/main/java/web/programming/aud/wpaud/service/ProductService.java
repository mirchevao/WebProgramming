package web.programming.aud.wpaud.service;

import web.programming.aud.wpaud.model.Category;
import web.programming.aud.wpaud.model.Manufacturer;
import web.programming.aud.wpaud.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> findAll();

    public Optional<Product> findById(Long id);

    public Optional<Product> findByName(String name);

    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId);

    public void deleteById(Long id);
}
