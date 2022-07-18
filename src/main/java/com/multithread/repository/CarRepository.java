package com.multithread.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multithread.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
