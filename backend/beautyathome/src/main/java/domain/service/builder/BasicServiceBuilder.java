package domain.service.builder;

import java.util.ArrayList;
import java.util.List;

import domain.service.ServiceComponent;
import domain.service.ServiceLeaf;
import domain.service.image.ImageReference;

public class BasicServiceBuilder implements ServiceBuilder {

    private String name;
    private String description;
    private double price;
    private int duration;
    private final List<ImageReference> images = new ArrayList<>();

    @Override
    public void reset() {
        name = null;
        description = null;
        price = 0;
        duration = 0;
        images.clear();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void setDuration(int durationMinutes) {
        this.duration = durationMinutes;
    }

    @Override
    public void addImage(String url) {
        images.add(new ImageReference(url));
    }

    @Override
    public ServiceComponent getResult() {
        ServiceLeaf service = new ServiceLeaf(name, description, price, duration, new ArrayList<>(images));
        reset();
        return service;
    }
}
