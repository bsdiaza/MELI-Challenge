package org.meli.resistance.rescueapi.domain.ports;

import org.springframework.validation.annotation.Validated;

import java.awt.Point;

@Validated
public interface LocationPort {
    Point GetLocation(double[] distances);
}
