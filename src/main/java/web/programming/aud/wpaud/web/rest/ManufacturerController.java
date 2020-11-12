package web.programming.aud.wpaud.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.programming.aud.wpaud.model.Manufacturer;
import web.programming.aud.wpaud.service.ManufacturerService;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll() {
        return this.manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(Long id){
        return this.manufacturerService.findById(id)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> save(String name, String address) {
        return this.manufacturerService.save(name,address)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Manufacturer> deleteById(@PathVariable Long id) {
        if(this.manufacturerService.deleteById(id))
            return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

}
