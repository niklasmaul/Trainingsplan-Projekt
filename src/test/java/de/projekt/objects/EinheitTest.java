package de.projekt.objects;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class EinheitTest {

    @org.junit.jupiter.api.Test
    void kcalverbrannt() {
        Einheit e1 = new Einheit("Brustpresse", 80.5,
                "Butterfly", 17.0, "Schulterpresse", 37.0,
                "Laufband", 20, LocalDate.now().minusDays(10));
        Einheit e2 = new Einheit("High Row", 79.5,
                "Latzug", 80.0, "Curls", 20,
                "Stairmaster", 15, LocalDate.now().minusDays(11));

        double v1 = e1.kcalverbrannt();
        double v2 = e2.kcalverbrannt();
        assertEquals(105.2, v1 );
        assertEquals(147,v2);
    }
}