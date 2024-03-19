package com.itarhitekture.grpc;

import com.itarhitekture.*;
import com.itarhitekture.model.Reservation;
import com.itarhitekture.service.ReservationService;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

@GrpcService
public class ReservationGrpcServiceMain extends ReservationServiceGrpc.ReservationServiceImplBase {
    @Inject
    ReservationService reservationService;

    @Override
    public void createReservation(CreateReservationRequest request, StreamObserver<ReservationReply> responseObserver) {
        Reservation reservation = new Reservation();
        reservation.userId = request.getUserId();
        reservation.restaurantId = request.getRestaurantId();
        reservation.dateAndTime = request.getDateAndTime();

        reservation = reservationService.createReservation(reservation);

        ReservationReply response = ReservationReply.newBuilder()
                .setId(reservation.id)
                .setUserId(reservation.userId)
                .setRestaurantId(reservation.restaurantId)
                .setDateAndTime(reservation.dateAndTime)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getReservation(GetReservationRequest request, StreamObserver<ReservationReply> responseObserver) {
        Reservation reservation = reservationService.getReservation(request.getId());

        ReservationReply response = ReservationReply.newBuilder()
                .setId(reservation.id)
                .setUserId(reservation.userId)
                .setRestaurantId(reservation.restaurantId)
                .setDateAndTime(reservation.dateAndTime)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateReservation(UpdateReservationRequest request, StreamObserver<ReservationReply> responseObserver) {
        Reservation reservation = new Reservation();
        reservation.id = request.getId();
        reservation.userId = request.getUserId();
        reservation.restaurantId = request.getRestaurantId();
        reservation.dateAndTime = request.getDateAndTime();

        reservation = reservationService.updateReservation(reservation);

        ReservationReply response = ReservationReply.newBuilder()
                .setId(reservation.id)
                .setUserId(reservation.userId)
                .setRestaurantId(reservation.restaurantId)
                .setDateAndTime(reservation.dateAndTime)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteReservation(DeleteReservationRequest request, StreamObserver<DeleteReservationReply> responseObserver) {
        boolean success = reservationService.deleteReservation(request.getId());

        DeleteReservationReply response = DeleteReservationReply.newBuilder()
                .setSuccess(success)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}