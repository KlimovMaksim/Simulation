package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;

abstract public class Entity {
    public Coordinate coordinate;

    public Entity(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
