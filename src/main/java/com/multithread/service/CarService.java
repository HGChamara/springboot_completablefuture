package com.multithread.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.multithread.model.Car;

public interface CarService {
	CompletableFuture<List<Car>> saveCars(List<Car> cars) throws ArithmeticException;
	CompletableFuture<List<Car>> getAllCars();
}
