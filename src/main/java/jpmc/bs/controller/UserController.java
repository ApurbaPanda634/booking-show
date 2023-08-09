package jpmc.bs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jpmc.bs.entity.ShowEntity;
import jpmc.bs.model.BookTicketModel;
import jpmc.bs.model.CancelTicketModel;
import jpmc.bs.model.SetupShowModel;
import jpmc.bs.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller", description = "Restful API for user to book show")
public record UserController(UserService userService) {

    @PostMapping("/cancel")
    @Operation(summary = "book a show", description = "book a show and return the created ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cancelled successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid data provided")
    })
    public ResponseEntity<String> cancel(@RequestBody CancelTicketModel cancelTicketModel) {
        String ticketID = userService.cancel(cancelTicketModel);
        return ResponseEntity.ok("Cancelled ticketID: "+ ticketID);
    }

    @PostMapping("/book")
    @Operation(summary = "book a show", description = "book a show and return the created ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket Booked successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid booking data provided")
    })
    public ResponseEntity<String> book(@RequestBody BookTicketModel bookTicketModel) {
        String ticketID = userService.book(bookTicketModel);
        return ResponseEntity.ok("Booking Done for ticketID: "+ ticketID);
    }

    @GetMapping("/availability/{showNumber}")
    @Operation(summary = "view a show", description = "dummy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    public ResponseEntity<String> getAvailableSeats(@PathVariable int showNumber) {
        String availableSeat = userService.getAvailableSeats(showNumber);
        return ResponseEntity.ok(availableSeat);
    }
}
