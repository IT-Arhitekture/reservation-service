package com.itarhitekture;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class ReservationServiceApplication {

    public static void main(String... args) {
        Quarkus.run(args);
    }
}