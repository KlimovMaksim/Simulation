package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;

public class Herbivore extends Creature{
    public Herbivore(Coordinate coordinate) {
        super(coordinate);
        speed = 1;
    }

    @Override
    protected void makeMove() {

    }
}
