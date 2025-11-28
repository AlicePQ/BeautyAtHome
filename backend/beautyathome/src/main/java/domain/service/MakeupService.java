package domain.service;

import java.util.List;

import domain.service.image.ImageReference;

public class MakeupService extends ServiceLeaf {

	public MakeupService(String name,
						 String description,
						 double price,
						 int durationMin,
						 List<ImageReference> images) {
		super(name, description, price, durationMin, images);
		setCategory(new Category("Makeup"));
	}
}
