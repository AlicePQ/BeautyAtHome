package domain.service.image;

public class ImageReference {

    private String url;

    public ImageReference(String url) {
        this.url = url;
    }

    public ImageReference() {}

    public String getUrl() {
        return url;
    }
}
