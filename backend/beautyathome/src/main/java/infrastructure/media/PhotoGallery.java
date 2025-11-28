package infrastructure.media;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import domain.booking.Booking;
import domain.service.image.Photo;
import infrastructure.persistence.dao.BookingDAO;

public class PhotoGallery {

	private final StorageAdapter storageAdapter;
	private final BookingDAO bookingDAO;
	private final List<Photo> photos = new CopyOnWriteArrayList<>();

	public PhotoGallery(StorageAdapter storageAdapter, BookingDAO bookingDAO) {
		this.storageAdapter = storageAdapter;
		this.bookingDAO = bookingDAO;
	}

	public void addPhoto(String bookingId, String url, boolean isPublic) {
		Booking booking = bookingDAO.findById(bookingId);
		if (booking == null) {
			throw new IllegalArgumentException("Booking not found for photo upload");
		}
		String storedUrl = storageAdapter.store(bookingId, url);
		photos.add(new Photo(bookingId, booking.getProfessionalId(), storedUrl, isPublic));
	}

	public List<Photo> listByProfessional(String professionalId) {
		return Collections.unmodifiableList(
			photos.stream()
				.filter(photo -> photo.getProfessionalId().equals(professionalId))
				.collect(Collectors.toList())
		);
	}
}
