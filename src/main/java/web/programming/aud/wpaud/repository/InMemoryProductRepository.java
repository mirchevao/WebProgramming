package web.programming.aud.wpaud.repository;

import org.springframework.stereotype.Repository;
import web.programming.aud.wpaud.bootstrap.DataHolder;
import web.programming.aud.wpaud.model.Category;
import web.programming.aud.wpaud.model.Manufacturer;
import web.programming.aud.wpaud.model.Product;

import javax.xml.crypto.Data;
import java.net.PortUnreachableException;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {

    public List<Product> findAll() {

        return DataHolder.products;
    }

    public Optional<Product> findById(Long id) {

        return DataHolder.products.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Product> findByName(String name) {

        return DataHolder.products.stream().filter(i -> i.getName().equals(name)).findFirst();
    }

    public Optional<Product> save(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {

        DataHolder.products.removeIf(i -> i.getName().equals(name));
        Product product = new Product(name, price, quantity, category, manufacturer);
        DataHolder.products.add(product);
        return Optional.of(product);
    }

    public void deleteById(Long id) {

        DataHolder.products.removeIf(i -> i.getId().equals(id));
    }


}
