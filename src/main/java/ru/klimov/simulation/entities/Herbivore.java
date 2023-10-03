package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;
import ru.klimov.simulation.ShiftCoordinate;

import java.util.Set;

public class Herbivore extends Creature{
    public Herbivore(Coordinate coordinate) {
        super(coordinate);
        speed = 1;
        // add target
    }

    @Override
    public void makeMove() {

    }

    @Override
    protected Set<ShiftCoordinate> getEntityMoves() {
        return null;
    }
}
