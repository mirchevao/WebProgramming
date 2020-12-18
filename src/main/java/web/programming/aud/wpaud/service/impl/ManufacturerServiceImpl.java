package web.programming.aud.wpaud.service.impl;

import org.springframework.stereotype.Service;
import web.programming.aud.wpaud.bootstrap.DataHolder;
import web.programming.aud.wpaud.model.Manufacturer;
import web.programming.aud.wpaud.repository.jpa.ManufacturerRepository;
import web.programming.aud.wpaud.service.ManufacturerService;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {

        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name, address)));
    }

    @Override
    public void deleteById(Long id) {
        deleteById(id);
    }
}
