package testtask.shift.shopapi.controller;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import testtask.shift.shopapi.model.monitor.Monitor;
import testtask.shift.shopapi.service.MonitorService;

@RestController
@RequestMapping("/api/monitors")
public class MonitorController {
    private final MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @Operation(summary = "Get monitors list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monitors list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Monitor.class))))})
    @GetMapping(value = {"", "/"}, produces = "application/json")
    public @NotNull
    Iterable<Monitor> getMonitors() {
        return monitorService.getAllMonitors();
    }

    @Operation(summary = "Get monitor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monitor by this ID was found",
                    content = @Content(schema = @Schema(implementation = Monitor.class))),
            @ApiResponse(responseCode = "404", description = "Monitor by this ID was not found",
                    content = @Content(schema = @Schema(implementation = Monitor.class)))})
    @GetMapping(value = "/{id}", produces = "application/json")
    public Monitor getMonitor(@PathVariable long id) {
        return monitorService.getMonitor(id);
    }

    @Operation(summary = "Create new monitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New monitor was created",
                    content = @Content(schema = @Schema(implementation = Monitor.class)))})
    @PostMapping(value = "/add", produces = "application/json")
    public Monitor createNewMonitor(@RequestBody Monitor newMonitor) {
        return monitorService.save(newMonitor);
    }

    @Operation(summary = "Edit existing monitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monitor was edited",
                    content = @Content(schema = @Schema(implementation = Monitor.class)))})
    @PutMapping(value = "/{id}", produces = "application/json")
    public Monitor editMonitor(@PathVariable long id,
                               @RequestBody @org.jetbrains.annotations.NotNull Monitor newMonitor) {
        monitorService.getMonitor(id);
        newMonitor.setId(id);
        return monitorService.save(newMonitor);
    }
}
