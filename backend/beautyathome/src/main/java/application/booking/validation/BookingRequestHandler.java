package application.booking.validation;

import application.booking.BookingRequest;

public abstract class BookingRequestHandler {

    protected BookingRequestHandler next;

    public BookingRequestHandler setNext(BookingRequestHandler next) {
        this.next = next;
        return next;
    }

    public boolean handle(BookingRequest request) {
        if (!doHandle(request)) {
            return false;
        }
        if (next == null) {
            return true;
        }
        return next.handle(request);
    }

    protected abstract boolean doHandle(BookingRequest request);
}
