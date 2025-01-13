package testtask.shift.shopapi.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.shift.shopapi.model.pc.PersonalComputer;
import testtask.shift.shopapi.repository.PersonalComputerRepository;

@Service
@Transactional
public class PersonalComputerServiceImpl implements PersonalComputerService {
    private final PersonalComputerRepository personalComputerRepository;

    public PersonalComputerServiceImpl(PersonalComputerRepository personalComputerRepository) {
        this.personalComputerRepository = personalComputerRepository;
    }

    @Override
    public Iterable<PersonalComputer> getAllPersonalComputers() {
        return personalComputerRepository.findAll();
    }

    @Override
    public PersonalComputer getPersonalComputer(long id) {
        return personalComputerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PC not found"));
    }

    @Override
    public PersonalComputer save(PersonalComputer personalComputer) {
        return personalComputerRepository.save(personalComputer);
    }
}
