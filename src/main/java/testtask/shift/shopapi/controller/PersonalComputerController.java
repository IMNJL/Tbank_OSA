package testtask.shift.shopapi.controller;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import testtask.shift.shopapi.model.pc.PersonalComputer;
import testtask.shift.shopapi.service.PersonalComputerService;

@RestController
@RequestMapping("/api/pcs")
public class PersonalComputerController {
    private final PersonalComputerService personalComputerService;

    public PersonalComputerController(PersonalComputerService personalComputerService) {
        this.personalComputerService = personalComputerService;
    }

    @Operation(summary = "Get a personal computers list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personal computers list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonalComputer.class))))})
    @GetMapping(value = {"", "/"}, produces = "application/json")
    public @NotNull
    Iterable<PersonalComputer> getPersonalComputers() {
        return personalComputerService.getAllPersonalComputers();
    }

    @Operation(summary = "Get PC by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PC by this ID was found",
                    content = @Content(schema = @Schema(implementation = PersonalComputer.class))),
            @ApiResponse(responseCode = "404", description = "PC by this ID was not found",
                    content = @Content(schema = @Schema(implementation = PersonalComputer.class)))})
    @GetMapping(value = "/{id}", produces = "application/json")
    public PersonalComputer getPersonalComputer(@PathVariable long id) {
        return personalComputerService.getPersonalComputer(id);
    }

    @Operation(summary = "Create new PC")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New PC was created",
                    content = @Content(schema = @Schema(implementation = PersonalComputer.class)))})
    @PostMapping(value = "/add", produces = "application/json")
    public PersonalComputer createNewPersonalComputer(@RequestBody PersonalComputer newPersonalComputer) {
        return personalComputerService.save(newPersonalComputer);
    }

    @Operation(summary = "Edit existing PC")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PC was edited",
                    content = @Content(schema = @Schema(implementation = PersonalComputer.class)))})
    @PutMapping(value = "/{id}", produces = "application/json")
    public PersonalComputer editPersonalComputer(@PathVariable long id,
                                                 @RequestBody @org.jetbrains.annotations.NotNull PersonalComputer newPersonalComputer) {
        personalComputerService.getPersonalComputer(id);
        newPersonalComputer.setId(id);
        return personalComputerService.save(newPersonalComputer);
    }
}
