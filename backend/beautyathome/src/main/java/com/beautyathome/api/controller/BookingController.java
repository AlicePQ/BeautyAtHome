package main.java.com.beautyathome.api.controller;


import main.java.com.beautyathome.application.facade.BeautyAtHomeFacade;
import main.java.com.beautyathome.domain.booking.Booking;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BeautyAtHomeFacade facade;

    public BookingController(BeautyAtHomeFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public Booking createBooking(@RequestParam String clientId,
                                 @RequestParam String professionalId,
                                 @RequestParam String serviceId,
                                 @RequestParam
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                 LocalDateTime dateTime) {
        return facade.bookService(clientId, professionalId, serviceId, dateTime);
    }
}
