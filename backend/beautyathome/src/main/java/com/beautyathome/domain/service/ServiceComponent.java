package main.java.com.beautyathome.domain.service;


import main.java.com.beautyathome.domain.service.visitor.ServiceVisitor;
import main.java.com.beautyathome.domain.service.image.ImageReference;

import java.util.List;

public interface ServiceComponent {

    String getName();
    String getDescription();
    double getPrice();
    int getDurationMin();
    List<ImageReference> getImages();

    void execute();

    void accept(ServiceVisitor visitor);
}
