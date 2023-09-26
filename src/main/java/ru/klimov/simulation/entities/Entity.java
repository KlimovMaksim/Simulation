package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;

abstract public class Entity {
    protected Coordinate coordinate;
    public final Symbols symbol;

    public Entity(Coordinate coordinate, Symbols symbol) {
        this.coordinate = coordinate;
        this.symbol = symbol;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
