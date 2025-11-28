package domain.service.visitor;

import domain.service.ServiceComposite;
import domain.service.ServiceLeaf;

public interface ServiceVisitor {

    void visitServiceLeaf(ServiceLeaf leaf);

    void visitServiceComposite(ServiceComposite composite);
}

