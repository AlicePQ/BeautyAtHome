package domain.service.image;

public class Photo {

    private final String bookingId;
    private final String professionalId;
    private final String url;
    private final boolean isPublic;

    public Photo(String bookingId, String professionalId, String url, boolean isPublic) {
        this.bookingId = bookingId;
        this.professionalId = professionalId;
        this.url = url;
        this.isPublic = isPublic;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getProfessionalId() {
        return professionalId;
    }

    public String getUrl() {
        return url;
    }

    public boolean isPublic() {
        return isPublic;
    }
}
