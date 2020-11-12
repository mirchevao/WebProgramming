package web.programming.aud.wpaud.service.impl;

import org.springframework.stereotype.Service;
import web.programming.aud.wpaud.bootstrap.DataHolder;
import web.programming.aud.wpaud.model.Manufacturer;
import web.programming.aud.wpaud.repository.InMemoryManufacturerRepository;
import web.programming.aud.wpaud.service.ManufacturerService;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final InMemoryManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(InMemoryManufacturerRepository manufacturerRepository) {

        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.fingAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        Manufacturer manufacturer = new Manufacturer(name, address);
        DataHolder.manufacturers.add(manufacturer);
        return Optional.of(manufacturer);
    }

    @Override
    public boolean deleteById(Long id) {
        return DataHolder.manufacturers.removeIf(i -> i.getId().equals(id));
    }
}
