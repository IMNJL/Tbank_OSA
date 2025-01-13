package testtask.shift.shopapi.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.shift.shopapi.model.laptop.Laptop;
import testtask.shift.shopapi.repository.LaptopRepository;

@Service
@Transactional
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepository laptopRepository;

    public LaptopServiceImpl(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Override
    public Iterable<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    @Override
    public Laptop getLaptop(long id) {
        return laptopRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laptop not found"));
    }

    @Override
    public Laptop save(Laptop laptop) {
        return laptopRepository.save(laptop);
    }
}
