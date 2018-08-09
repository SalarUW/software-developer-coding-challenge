package com.salar.carauction.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Bid {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Car car;

    private String username;
    private Integer amount;
    private String carName;

    public Bid(String username, Integer amount) {
        this.username = username;
        this.amount = amount;
    }
}
