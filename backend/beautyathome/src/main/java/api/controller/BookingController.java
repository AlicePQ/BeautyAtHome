package api.controller;


import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.facade.BeautyAtHomeFacade;
import domain.booking.Booking;

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
                                 @RequestParam(required = false) String zone,
                                 @RequestParam
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                 LocalDateTime dateTime) {
        return facade.bookService(clientId, professionalId, serviceId, dateTime, zone);
    }
}
