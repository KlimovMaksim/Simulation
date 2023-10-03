package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;
import ru.klimov.simulation.ShiftCoordinate;

import java.util.Set;

public class Predator extends Creature{
    public final int damage = 5;

    public Predator(Coordinate coordinate) {
        super(coordinate);
        speed = 2;
        // target
    }

    @Override
    public void makeMove() {

    }

    @Override
    protected Set<ShiftCoordinate> getEntityMoves() {
        return null;
    }

}
