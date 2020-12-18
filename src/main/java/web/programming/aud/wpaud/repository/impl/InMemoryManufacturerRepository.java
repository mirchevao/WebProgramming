package web.programming.aud.wpaud.repository.impl;

import org.springframework.stereotype.Repository;
import web.programming.aud.wpaud.bootstrap.DataHolder;
import web.programming.aud.wpaud.model.Manufacturer;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {

    public List<Manufacturer> fingAll() {

        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id) {

        return DataHolder.manufacturers.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}
