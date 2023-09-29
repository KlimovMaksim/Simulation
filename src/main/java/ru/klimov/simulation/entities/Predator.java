package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;

public class Predator extends Creature{
    public final int damage = 5;

    public Predator(Coordinate coordinate) {
        super(coordinate);
        speed = 2;
    }

    @Override
    protected void makeMove() {
    }
}
