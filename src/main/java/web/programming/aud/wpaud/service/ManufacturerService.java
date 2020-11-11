package web.programming.aud.wpaud.service;

import web.programming.aud.wpaud.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    public List<Manufacturer> findAll();

    public Optional<Manufacturer> findById(Long id);
}
