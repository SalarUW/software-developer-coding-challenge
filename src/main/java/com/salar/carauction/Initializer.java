package com.salar.carauction;

import com.salar.carauction.model.Bid;
import com.salar.carauction.model.Car;
import com.salar.carauction.model.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {
    private final CarRepository repository;

    @Override
    public void run(String...strings) {
        Car car = new Car("Toyota Corolla 2015");
        Car car2= new Car("Honda Civic 2010");

        car.addBid(new Bid("user1", 10000));
        car2.addBid(new Bid("user1", 2000));

        repository.save(car);
        repository.save(car2);
    }
}
