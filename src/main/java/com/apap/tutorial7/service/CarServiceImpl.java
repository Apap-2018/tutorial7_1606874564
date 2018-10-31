package com.apap.tutorial7.service;
import java.util.List;
import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.repository.CarDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImpl implements CarService{
	@Autowired
	private CarDb carDb;
	
	@Override
	public CarModel addCar(CarModel car) {
		carDb.save(car);
		return car;
	}
	
	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
	}
	
	@Override
	public void updateCar(Long id, CarModel newCar) {
		CarModel carUpdated = carDb.getOne(id);
		carUpdated.setAmount(newCar.getAmount());
		carUpdated.setBrand(newCar.getBrand());
		carUpdated.setType(newCar.getType());
		carUpdated.setPrice(newCar.getPrice());
		carDb.save(carUpdated);	
	}
	
	@Override
	public CarModel getCar(Long id) {
		return carDb.findById(id).get();
	}

	@Override
	public List<CarModel> viewAllCar() {
		return carDb.findAll();
	}
 
}
