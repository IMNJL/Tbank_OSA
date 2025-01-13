package testtask.shift.shopapi.repository;

import org.springframework.data.repository.CrudRepository;
import testtask.shift.shopapi.model.laptop.Laptop;

public interface LaptopRepository extends CrudRepository<Laptop, Long> {
}
