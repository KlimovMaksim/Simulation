package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;

abstract public class Creature extends Entity{
    public final int maximumHealPoints = 10;
    protected int currentHealPoints;

    public Creature(Coordinate coordinate) {
        super(coordinate);
    }

    abstract protected void makeMove();
}
