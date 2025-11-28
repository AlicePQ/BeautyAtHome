package domain.service.composite;

import domain.service.ServiceComponent;

public interface ServiceIterator {
    boolean hasNext();
    ServiceComponent next();
}
