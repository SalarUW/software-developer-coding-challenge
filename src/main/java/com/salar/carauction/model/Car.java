package com.salar.carauction.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer highestBid = 0;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bid> bids = new ArrayList<>();

    public Car(String name) {
        this.name = name;
    }

    public void addBid(Bid bid) {
        if (bid.getAmount() > this.highestBid) {
            this.highestBid = bid.getAmount();
        }
        bids.add(bid);
        bid.setCar(this);
    }

    // won't do removeBid
}
