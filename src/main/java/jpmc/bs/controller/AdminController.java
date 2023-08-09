package jpmc.bs.controller;

import jpmc.bs.model.AdminViewModel;
import jpmc.bs.model.SetupShowModel;
import jpmc.bs.entity.ShowEntity;
import jpmc.bs.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Controller", description = "Restful API for managing shows by admin.")
public record AdminController(AdminService adminService) {

    @GetMapping("/view/{showNumber}")
    @Operation(summary = "view a show", description = "dummy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    public ResponseEntity<AdminViewModel> viewShow(@PathVariable int showNumber) {
        AdminViewModel adminViewModel = adminService.viewShow(showNumber);
        return ResponseEntity.ok(adminViewModel);
    }

    @PostMapping("/setup")
    @Operation(summary = "Setup a show", description = "setup the show with seat allocation and return the created show")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Show setup successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid show data provided")
    })
    public ResponseEntity<String> setup(@RequestBody SetupShowModel setupShowModel) {
        ShowEntity showEntity = adminService.setup(setupShowModel);
        return ResponseEntity.ok("Setup completed for show: " + showEntity.getShowNumber());
    }
}
