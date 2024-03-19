package com.itarhitekture.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Reservation extends PanacheEntity {
    public long dateAndTime;

    public String userId;

    public String restaurantId;
}