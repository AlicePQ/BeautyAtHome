package main.java.com.beautyathome.domain.service.visitor;

import main.java.com.beautyathome.domain.service.ServiceLeaf;
import main.java.com.beautyathome.domain.service.ServiceComposite;

public interface ServiceVisitor {

    void visitServiceLeaf(ServiceLeaf leaf);

    void visitServiceComposite(ServiceComposite composite);
}

