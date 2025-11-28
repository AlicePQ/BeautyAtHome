package domain.service;


import java.util.ArrayList;
import java.util.List;

import domain.service.composite.CompositeServiceIterator;
import domain.service.composite.ServiceIterator;
import domain.service.image.ImageReference;
import domain.service.visitor.ServiceVisitor;

public class ServiceComposite implements ServiceComponent {

    private final String name;
    private final String description;
    private final List<ServiceComponent> children = new ArrayList<>();

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
