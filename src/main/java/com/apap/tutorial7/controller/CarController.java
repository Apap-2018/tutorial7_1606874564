package com.apap.tutorial7.controller;
import com.apap.tutorial7.model.*;
import com.apap.tutorial7.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	// POST add car
	@PostMapping(value = "/add")
	private CarModel addCarSubmit(@RequestBody CarModel car) {
		return carService.addCar(car);
	}
	
	// GET all car
	@GetMapping()
	private List<CarModel> viewAllCar(Model model){
		List<CarModel> listCar = carService.viewAllCar();
		for(CarModel car:listCar) {
			car.setDealer(null);
		}
		return listCar;
	}
	
	// GET car
	@GetMapping(value= "/{carId}")
	private CarModel viewCar(@PathVariable("carId") long carId, Model model) {
		CarModel theCar = carService.getCar(carId);
		theCar.setDealer(null);
		return theCar;
	}
	
	// DELETE car
	@DeleteMapping(value="/delete")
	private String deleteCar(@RequestParam("carId") long id, Model model){
		CarModel car = carService.getCar(id);
		carService.deleteCar(car);
		return "car has been deleted";
	}
	
	// PUT UPDATE car
	@PutMapping(value= "/{id}")
	private String updateCarSubmit(@PathVariable(value = "id") long id, 
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "amount", required = false) Integer amount,
			@RequestParam(value = "dealerId", required = false) long dealerId,
			@RequestParam(value = "price", required = false) long price) {
		CarModel car = carService.getCar(id);
		if(car.equals(null)) {
			return "Couldn't find your dealer";
		}
		if(brand != null) {
			car.setBrand(brand);
		}
		if(type != null) {
			car.setType(type);
		}
		if(amount != 0) {
			car.setAmount(amount);
		}
		if(dealerId != 0) {
			Long idDealer = dealerId;
			DealerModel dealer = dealerService.getDealerDetailById(idDealer).get();
			car.setDealer(dealer);
		}
		if(price != 0) {
			car.setPrice(price);
		}
		carService.updateCar(id, car);
		return "car update success";
		
	}
}
