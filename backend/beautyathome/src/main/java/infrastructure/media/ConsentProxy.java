package infrastructure.media;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.service.image.Photo;

public class ConsentProxy {

	private final PhotoGallery gallery;
	private final Set<String> consentedBookings = new HashSet<>();

	public ConsentProxy(PhotoGallery gallery) {
		this.gallery = gallery;
	}

	public void addConsent(String bookingId) {
		consentedBookings.add(bookingId);
	}

	public void addPhoto(String bookingId, String url, boolean isPublic) {
		boolean allowed = isPublic && consentedBookings.contains(bookingId);
		gallery.addPhoto(bookingId, url, allowed);
	}

	public List<Photo> listByProfessional(String professionalId) {
		return gallery.listByProfessional(professionalId);
	}
}
