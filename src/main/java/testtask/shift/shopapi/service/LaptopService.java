package testtask.shift.shopapi.service;

import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;
import testtask.shift.shopapi.model.laptop.Laptop;

@Validated
public interface LaptopService {
    @NotNull
    Iterable<Laptop> getAllLaptops();

    Laptop getLaptop(long id);

    Laptop save(Laptop laptop);
}
