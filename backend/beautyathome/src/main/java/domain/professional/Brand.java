package domain.professional;

public class Brand {

    private String name;
    private String logoUrl;

    public Brand(String name, String logoUrl) {
        this.name = name;
        this.logoUrl = logoUrl;
    }

    public Brand() {}

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }
}

