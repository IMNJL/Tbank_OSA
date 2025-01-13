package testtask.shift.shopapi.controller;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import testtask.shift.shopapi.model.laptop.Laptop;
import testtask.shift.shopapi.service.LaptopService;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {
    private final LaptopService laptopService;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @Operation(summary = "Get laptops list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Laptops list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Laptop.class))))})
    @GetMapping(value = {"", "/"}, produces = "application/json")
    public @NotNull
    Iterable<Laptop> getLaptops() {
        return laptopService.getAllLaptops();
    }

    @Operation(summary = "Get laptop by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Laptop by this ID was found",
                    content = @Content(schema = @Schema(implementation = Laptop.class))),
            @ApiResponse(responseCode = "404", description = "Laptop by this ID was not found",
                    content = @Content(schema = @Schema(implementation = Laptop.class)))})
    @GetMapping(value = "/{id}", produces = "application/json")
    public Laptop getLaptop(@PathVariable long id) {
        return laptopService.getLaptop(id);
    }

    @Operation(summary = "Create new laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New laptop was created",
                    content = @Content(schema = @Schema(implementation = Laptop.class)))})
    @PostMapping(value = "/add", produces = "application/json")
    public Laptop createNewHLaptop(@RequestBody Laptop newLaptop) {
        return laptopService.save(newLaptop);
    }

    @Operation(summary = "Edit existing laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Laptop was edited",
                    content = @Content(schema = @Schema(implementation = Laptop.class)))})
    @PutMapping(value = "/{id}", produces = "application/json")
    public Laptop editLaptop(@PathVariable long id,
                             @RequestBody @org.jetbrains.annotations.NotNull Laptop newLaptop) {
        laptopService.getLaptop(id);
        newLaptop.setId(id);
        return laptopService.save(newLaptop);
    }
}
