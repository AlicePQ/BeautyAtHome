package main.java.com.beautyathome.domain.service;

import main.java.com.beautyathome.domain.service.visitor.ServiceVisitor;
import main.java.com.beautyathome.domain.service.image.ImageReference;
import java.util.List;

public class ServiceLeaf implements ServiceComponent {

    private String name;
    private String description;
    private double price;
    private int durationMin;
    private List<ImageReference> images;

    public ServiceLeaf(String name, String description, double price, int durationMin, List<ImageReference> images) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationMin = durationMin;
        this.images = images;
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
