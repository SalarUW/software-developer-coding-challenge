package com.salar.carauction.web;

import com.salar.carauction.model.Bid;
import com.salar.carauction.model.Car;
import com.salar.carauction.model.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/home")
public class AuctionController {
    private CarRepository repository;

    public AuctionController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    Collection<Car> getCars() {
        return repository.findAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public void makeBid(@RequestBody Bid bid) {
        String carName = bid.getCarName();
        System.out.println(carName + "\n");
        Car car = repository.findByName(carName);
        car.addBid(bid);
        repository.save(car);
    }
}
