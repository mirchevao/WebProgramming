package web.programming.aud.wpaud.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.programming.aud.wpaud.model.Manufacturer;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>
{

}
