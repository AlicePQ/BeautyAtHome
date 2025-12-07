package ui.viewmodel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Form backing bean that captures professional registration data from the MVC layer.
 */
public class ProfessionalForm {

    private String id;
    private String type;
    private String name;
    private String photoUrl;
    private String experienceSummary;
    private String coverageAreas; // comma separated
    private String brandName;
    private String brandLogo;
    private String brandProducts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getExperienceSummary() {
        return experienceSummary;
    }

    public void setExperienceSummary(String experienceSummary) {
        this.experienceSummary = experienceSummary;
    }

    public String getCoverageAreas() {
        return coverageAreas;
    }

    public void setCoverageAreas(String coverageAreas) {
        this.coverageAreas = coverageAreas;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getBrandProducts() {
        return brandProducts;
    }

    public void setBrandProducts(String brandProducts) {
        this.brandProducts = brandProducts;
    }

    /**
     * @return coverage areas split by comma and trimmed
     */
    public List<String> coverageAsList() {
        if (coverageAreas == null || coverageAreas.isBlank()) {
            return List.of();
        }
        return Arrays.stream(coverageAreas.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * @return lista de productos patrocinados separados por coma.
     */
    public List<String> brandProductsAsList() {
        if (brandProducts == null || brandProducts.isBlank()) {
            return List.of();
        }
        return Arrays.stream(brandProducts.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
