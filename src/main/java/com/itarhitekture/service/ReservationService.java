package com.itarhitekture.service;

import com.itarhitekture.model.Reservation;
import com.itarhitekture.repository.ReservationRepository;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.List;

@ApplicationScoped
public class ReservationService {

    @Inject
    ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.listAll();
    }

    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        reservationRepository.persist(reservation);
        return reservation;
    }

    public Reservation updateReservation(Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(reservation.id);
        if (existingReservation != null) {
            existingReservation.userId = reservation.userId;
            existingReservation.restaurantId = reservation.restaurantId;
            existingReservation.dateAndTime = reservation.dateAndTime;
        }
        return existingReservation;
    }

    public boolean deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id);
        if (reservation != null) {
            reservationRepository.delete(reservation);
            return true;
        } else {
            return false;
        }
    }
}