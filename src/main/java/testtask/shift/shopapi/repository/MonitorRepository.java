package testtask.shift.shopapi.repository;

import org.springframework.data.repository.CrudRepository;
import testtask.shift.shopapi.model.monitor.Monitor;

public interface MonitorRepository extends CrudRepository<Monitor, Long> {
}
