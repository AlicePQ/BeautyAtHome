package domain.service;


import java.util.List;

import domain.service.image.ImageReference;
import domain.service.visitor.ServiceVisitor;

public interface ServiceComponent {

    String getName();
    String getDescription();
    double getPrice();
    int getDurationMin();
    List<ImageReference> getImages();

    void execute();

    void accept(ServiceVisitor visitor);
}
