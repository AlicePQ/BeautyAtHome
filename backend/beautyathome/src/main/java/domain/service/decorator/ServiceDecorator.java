package domain.service.decorator;

import domain.service.image.ImageReference;
import domain.service.ServiceComponent;
import domain.service.visitor.ServiceVisitor;

import java.util.List;

public abstract class ServiceDecorator implements ServiceComponent {

    protected ServiceComponent component;

    protected ServiceDecorator(ServiceComponent component) {
        this.component = component;
    }

    @Override
    public String getName() { return component.getName(); }

    @Override
    public String getDescription() { return component.getDescription(); }

    @Override
    public double getPrice() { return component.getPrice(); }

    @Override
    public int getDurationMin() { return component.getDurationMin(); }

    @Override
    public List<ImageReference> getImages() { return component.getImages(); }

    @Override
    public void execute() { component.execute(); }

    @Override
    public void accept(ServiceVisitor visitor) {
        component.accept(visitor);
    }
}
