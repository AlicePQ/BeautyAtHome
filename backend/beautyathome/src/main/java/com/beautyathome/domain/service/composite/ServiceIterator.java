package main.java.com.beautyathome.domain.service.composite;

import main.java.com.beautyathome.domain.service.ServiceComponent;

public interface ServiceIterator {
    boolean hasNext();
    ServiceComponent next();
}
