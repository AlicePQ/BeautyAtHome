package main.java.com.beautyathome.domain.service.visitor;


import main.java.com.beautyathome.domain.service.ServiceComposite;
import main.java.com.beautyathome.domain.service.ServiceLeaf;

public class PricingVisitor implements ServiceVisitor {

    private double totalPrice = 0.0;

    @Override
    public void visitServiceLeaf(ServiceLeaf leaf) {
        totalPrice += leaf.getPrice();
    }

    @Override
    public void visitServiceComposite(ServiceComposite composite) {
        // Ya se visitan sus hijos desde composite.accept(...)
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
