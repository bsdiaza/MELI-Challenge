package org.meli.resistance.rescueapi.application.services;

import org.jetbrains.annotations.NotNull;
import org.meli.resistance.rescueapi.domain.models.Satelite;
import org.meli.resistance.rescueapi.domain.ports.LocationPort;
import org.springframework.stereotype.Service;

import java.awt.Point;

@Service
public class LocationService implements LocationPort {

    @Override
    public Point GetLocation(Double @NotNull [] distances) {
        int satKenX = Satelite.KENOBI.getCoordinates().x;
        int satKenY = Satelite.KENOBI.getCoordinates().y;
        int satSkyX = Satelite.SKYWALKER.getCoordinates().x;
        int satSkyY = Satelite.SKYWALKER.getCoordinates().y;
        int satSatoX = Satelite.SATO.getCoordinates().x;
        int satSatoY = Satelite.SATO.getCoordinates().y;
        double satKenDist = distances[0];
        double satSkyDist = distances[1];
        double satSatoDist = distances[2];

        int A = -2 * satKenX + 2 * satSkyX;
        int B = -2 * satKenY + 2 * satSkyY;
        double C = Math.pow(satKenDist, 2) - Math.pow(satSkyDist, 2) - Math.pow(satKenX, 2) +
                Math.pow(satSkyX, 2) - Math.pow(satKenY, 2) + Math.pow(satSkyY, 2);
        int D = -2 * satSkyX + 2 * satSatoX;
        int E = -2 * satSkyY + 2 * satSatoY;
        double F = Math.pow(satSkyDist, 2) - Math.pow(satSatoDist, 2) - Math.pow(satSkyX, 2) +
                Math.pow(satSatoX, 2) - Math.pow(satSkyY, 2) + Math.pow(satSatoY, 2);

        int coordX = (int) (C * E - F * B) / (E * A - B * D);
        int coordY = (int) (C - A * coordX) / B;

        return new Point(coordX, coordY);
    }
}
