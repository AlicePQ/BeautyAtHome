package domain.service.composite;

import domain.service.ServiceComponent;
import java.util.Iterator;
import java.util.List;

public class CompositeServiceIterator implements ServiceIterator {

    private final Iterator<ServiceComponent> iterator;

    public CompositeServiceIterator(List<ServiceComponent> children) {
        this.iterator = children.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public ServiceComponent next() {
        return iterator.next();
    }
}
