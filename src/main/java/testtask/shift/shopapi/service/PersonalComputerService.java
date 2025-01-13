package testtask.shift.shopapi.service;

import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;
import testtask.shift.shopapi.model.pc.PersonalComputer;

@Validated
public interface PersonalComputerService {
    @NotNull
    Iterable<PersonalComputer> getAllPersonalComputers();

    PersonalComputer getPersonalComputer(long id);

    PersonalComputer save(PersonalComputer personalComputer);
}
