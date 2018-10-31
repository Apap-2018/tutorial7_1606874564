package com.apap.tutorial7.service;
import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;

public interface CarService {
	CarModel addCar(CarModel car);
	void deleteCar(CarModel car);
	void updateCar(Long id, CarModel car);
	CarModel getCar(Long id);
	List<CarModel> viewAllCar();
}
