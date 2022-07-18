package com.multithread.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.multithread.model.Car;
import com.multithread.repository.CarRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

	
	private final CarRepository carRepo;
	
	public CarServiceImpl(CarRepository carRepo) {
		this.carRepo = carRepo;
	}
	
	@Override
	@Async
	public CompletableFuture<List<Car>> saveCars(List<Car> cars) {
		log.info("start saveCars().");
		List<Car> savedCars = new ArrayList<Car>();
		savedCars = carRepo.saveAll(cars);
		return CompletableFuture.completedFuture(savedCars);
	}

	@Override
	@Async
	public CompletableFuture<List<Car>> getAllCars() {
		List<Car> cars = carRepo.findAll();
		return CompletableFuture.completedFuture(cars);
	}

}
