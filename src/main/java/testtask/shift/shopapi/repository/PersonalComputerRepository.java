package testtask.shift.shopapi.repository;

import org.springframework.data.repository.CrudRepository;
import testtask.shift.shopapi.model.pc.PersonalComputer;

public interface PersonalComputerRepository extends CrudRepository<PersonalComputer, Long> {
}
