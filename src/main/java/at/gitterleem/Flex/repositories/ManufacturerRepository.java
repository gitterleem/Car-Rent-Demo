package at.gitterleem.Flex.repositories;

import at.gitterleem.Flex.models.Manufacturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
}
