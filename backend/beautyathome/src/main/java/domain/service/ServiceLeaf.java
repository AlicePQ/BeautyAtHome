package domain.service;

import java.util.List;

import domain.service.image.ImageReference;
import domain.service.visitor.ServiceVisitor;

public class ServiceLeaf implements ServiceComponent {

    private final String name;
    private final String description;
    private final double price;
    private final int durationMin;
    private final List<ImageReference> images;
    private Category category;

    public ServiceLeaf(String name,
                       String description,
                       double price,
                       int durationMin,
                       List<ImageReference> images) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationMin = durationMin;
        this.images = images;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public double getPrice() { return price; }

    @Override
    public int getDurationMin() { return durationMin; }

    @Override
    public List<ImageReference> getImages() { return images; }

    @Override
    public void execute() {
        // Lógica de ejecución de un servicio simple
    }

    @Override
    public void accept(ServiceVisitor visitor) {
        visitor.visitServiceLeaf(this);
    }
}
