package org.meli.resistance.rescueapi.application.services;

import org.junit.jupiter.api.Test;
import org.meli.resistance.rescueapi.domain.models.Satelite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = { LocationService.class })
public class LocationServiceTests {

    @Autowired
    private LocationService locationService;

    @Test
    public void shouldCalculateShipCoordinates() {
        Point shipCoordinates= new Point(100, 100);
        double satKenDistance =
                calculateDistance(shipCoordinates, Satelite.KENOBI.getCoordinates());
        double satSkyDistance =
                calculateDistance(shipCoordinates, Satelite.SKYWALKER.getCoordinates());
        double satSatoDistance =
                calculateDistance(shipCoordinates, Satelite.SATO.getCoordinates());
        double[] distances = new double[]{ satKenDistance, satSkyDistance, satSatoDistance };

        Point calculatedShipCoordinates = locationService.GetLocation(distances);
        assertEquals(shipCoordinates.x, calculatedShipCoordinates.x);
        assertEquals(shipCoordinates.y, calculatedShipCoordinates.y);
    }

    public double calculateDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}
