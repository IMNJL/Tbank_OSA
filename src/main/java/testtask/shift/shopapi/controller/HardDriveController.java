package testtask.shift.shopapi.controller;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import testtask.shift.shopapi.model.hdd.HardDrive;
import testtask.shift.shopapi.service.HardDriveService;

@RestController
@RequestMapping("/api/hdds")
public class HardDriveController {
    private final HardDriveService hardDriveService;

    public HardDriveController(HardDriveService hardDriveService) {
        this.hardDriveService = hardDriveService;
    }

    @Operation(summary = "Get HDDs list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HDDs list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = HardDrive.class))))})
    @GetMapping(value = {"", "/"}, produces = "application/json")
    public @NotNull
    Iterable<HardDrive> getHardDrives() {
        return hardDriveService.getAllHardDrives();
    }

    @Operation(summary = "Get HDD by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HDD by this ID was found",
                    content = @Content(schema = @Schema(implementation = HardDrive.class))),
            @ApiResponse(responseCode = "404", description = "HDD by this ID was not found",
                    content = @Content(schema = @Schema(implementation = HardDrive.class)))})
    @GetMapping(value = "/{id}", produces = "application/json")
    public HardDrive getHardDrive(@PathVariable long id) {
        return hardDriveService.getHardDrive(id);
    }

    @Operation(summary = "Create new HDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New HDD was created",
                    content = @Content(schema = @Schema(implementation = HardDrive.class)))})
    @PostMapping(value = "/add", produces = "application/json")
    public HardDrive createNewHardDrive(@RequestBody HardDrive newHardDrive) {
        return hardDriveService.save(newHardDrive);
    }

    @Operation(summary = "Edit existing HDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HDD was edited",
                    content = @Content(schema = @Schema(implementation = HardDrive.class)))})
    @PutMapping(value = "/{id}", produces = "application/json")
    public HardDrive editHardDrive(@PathVariable long id,
                                   @RequestBody @org.jetbrains.annotations.NotNull HardDrive newHardDrive) {
        hardDriveService.getHardDrive(id);
        newHardDrive.setId(id);
        return hardDriveService.save(newHardDrive);
    }
}
