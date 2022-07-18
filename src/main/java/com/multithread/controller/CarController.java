package com.multithread.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.multithread.model.Car;
import com.multithread.service.CarService;

@RestController
@RequestMapping("/api/car")
public class CarController {
	
	private final CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity saveCars(@RequestBody List<Car> cars) {
		carService.saveCars(cars);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public @ResponseBody CompletableFuture<ResponseEntity> getAllCars() {
		return carService.getAllCars().<ResponseEntity>thenApply(ResponseEntity::ok)
				.exceptionally(handleGetFailure);
	}
	
	private static Function<Throwable, ResponseEntity<? extends List<Car>>> handleGetFailure = throwable -> {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	};
}
