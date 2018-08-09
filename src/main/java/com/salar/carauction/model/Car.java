package com.salar.carauction.model;

import lombok.Data;

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

    private Integer highestBid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car", orphanRemoval = true)
    private List<Bid> bids = new ArrayList<>();

    public void addBid(Bid bid) {
        bids.add(bid);
        bid.setCar(this);
    }

    // won't do removeBid
}
