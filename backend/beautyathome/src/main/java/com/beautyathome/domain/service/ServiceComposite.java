package main.java.com.beautyathome.domain.service;


import main.java.com.beautyathome.domain.service.composite.CompositeServiceIterator;
import main.java.com.beautyathome.domain.service.composite.ServiceIterator;
import main.java.com.beautyathome.domain.service.visitor.ServiceVisitor;
import main.java.com.beautyathome.domain.service.image.ImageReference;

import java.util.ArrayList;
import java.util.List;

public class ServiceComposite implements ServiceComponent {

    private String name;
    private String description;
    private List<ServiceComponent> children = new ArrayList<>();

    public ServiceComposite(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(ServiceComponent component) {
        children.add(component);
    }

    public void remove(ServiceComponent component) {
        children.remove(component);
    }

    public ServiceIterator createIterator() {
        return new CompositeServiceIterator(children);
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public double getPrice() {
        return children.stream().mapToDouble(ServiceComponent::getPrice).sum();
    }

    @Override
    public int getDurationMin() {
        return children.stream().mapToInt(ServiceComponent::getDurationMin).sum();
    }

    @Override
    public List<ImageReference> getImages() {
        List<ImageReference> all = new ArrayList<>();
        for (ServiceComponent child : children) {
            all.addAll(child.getImages());
        }
        return all;
    }

    @Override
    public void execute() {
        for (ServiceComponent child : children) {
            child.execute();
        }
    }

    @Override
    public void accept(ServiceVisitor visitor) {
        visitor.visitServiceComposite(this);
        for (ServiceComponent child : children) {
            child.accept(visitor);
        }
    }
}
