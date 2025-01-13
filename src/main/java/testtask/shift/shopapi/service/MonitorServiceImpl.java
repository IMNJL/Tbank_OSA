package testtask.shift.shopapi.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.shift.shopapi.model.monitor.Monitor;
import testtask.shift.shopapi.repository.MonitorRepository;

@Service
@Transactional
public class MonitorServiceImpl implements MonitorService {
    private final MonitorRepository monitorRepository;

    public MonitorServiceImpl(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    @Override
    public Iterable<Monitor> getAllMonitors() {
        return monitorRepository.findAll();
    }

    @Override
    public Monitor getMonitor(long id) {
        return monitorRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor not found"));
    }

    @Override
    public Monitor save(Monitor monitor) {
        return monitorRepository.save(monitor);
    }
}
