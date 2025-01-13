package testtask.shift.shopapi.service;

import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;
import testtask.shift.shopapi.model.hdd.HardDrive;

@Validated
public interface HardDriveService {
    @NotNull
    Iterable<HardDrive> getAllHardDrives();

    HardDrive getHardDrive(long id);

    HardDrive save(HardDrive hardDrive);
}
