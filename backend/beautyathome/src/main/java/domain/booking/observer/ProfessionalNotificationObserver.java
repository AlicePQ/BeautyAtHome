package domain.booking.observer;

import domain.booking.Booking;
import domain.professional.Professional;

/**
 * Observer that allows the platform to notify professionals whenever booking
 * changes take place.
 */
public class ProfessionalNotificationObserver implements NotificationObserver {

	private final Professional professional;

	/**
	 * @param professional professional to notify
	 */
	public ProfessionalNotificationObserver(Professional professional) {
		this.professional = professional;
	}

	@Override
	public void update(Booking booking) {
		// Hook for notifying professionals (email, SMS, etc.)
	}
}
