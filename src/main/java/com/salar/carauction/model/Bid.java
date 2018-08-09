package com.salar.carauction.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Bid {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Car car;

    private String username;
    private Integer amount;
}
