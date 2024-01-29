package org.meli.resistance.rescueapi.domain.models;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Satelite {

    KENOBI("Kenobi", new Point(-500,-200)),
    SKYWALKER("Skywalker", new Point(100,-100)),
    SATO("Sato", new Point(500,100));


    private static final Map<String, Satelite> satelitesByName = new HashMap<>();

    static {
        for (Satelite sat : values()) {
            satelitesByName.put(sat.name, sat);
        }
    }

    private final String name;
    private Point coordinates;
    private double distance;

    Satelite(String _name, Point _coordinates) {
        this.name = _name;
        this.coordinates = _coordinates;
    }

    public static Satelite getSateliteByName(String satName) {
        return satelitesByName.get(satName);
    }

    public static List<String> getSatelitesNames() {
        return satelitesByName.keySet().stream().toList();
    }

    public String getName() {
        return name;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
