package domain.service;

import java.util.List;

import domain.service.image.ImageReference;

public class HaircutService extends ServiceLeaf {

	public HaircutService(String name,
						  String description,
						  double price,
						  int durationMin,
						  List<ImageReference> images) {
		super(name, description, price, durationMin, images);
		setCategory(new Category("Haircut"));
	}
}
