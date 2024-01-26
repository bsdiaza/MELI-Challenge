package org.meli.resistance.rescueapi.domain.models;

import java.awt.Point;

public enum Satelite {

    KENOBI("Kenobi", new Point(-500,-200)),
    SKYWALKER("Skywalker", new Point(100,-100)),
    SATO("Sato", new Point(500,100));
    private final String name;
    private Point coordinates;

    Satelite(String _name, Point _coordinates) {
        this.name = _name;
        this.coordinates = _coordinates;
    }

    public String getName() {
        return name;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }
}
