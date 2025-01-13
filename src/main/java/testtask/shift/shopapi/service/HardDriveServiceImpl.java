package testtask.shift.shopapi.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.shift.shopapi.model.hdd.HardDrive;
import testtask.shift.shopapi.repository.HardDriveRepository;

@Service
@Transactional
public class HardDriveServiceImpl implements HardDriveService {
    private final HardDriveRepository hardDriveRepository;

    public HardDriveServiceImpl(HardDriveRepository hardDriveRepository) {
        this.hardDriveRepository = hardDriveRepository;
    }

    @Override
    public Iterable<HardDrive> getAllHardDrives() {
        return hardDriveRepository.findAll();
    }

    @Override
    public HardDrive getHardDrive(long id) {
        return hardDriveRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HardDrive not found"));
    }

    @Override
    public HardDrive save(HardDrive hardDrive) {
        return hardDriveRepository.save(hardDrive);
    }
}
