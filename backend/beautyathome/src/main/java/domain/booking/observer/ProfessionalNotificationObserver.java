package domain.booking.observer;

import domain.booking.Booking;
import domain.professional.Professional;

public class ProfessionalNotificationObserver implements NotificationObserver {

	private final Professional professional;

	public ProfessionalNotificationObserver(Professional professional) {
		this.professional = professional;
	}

	@Override
	public void update(Booking booking) {
		// Hook for notifying professionals (email, SMS, etc.)
	}
}
