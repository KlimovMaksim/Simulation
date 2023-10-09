package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;
import ru.klimov.simulation.Map;

public class Herbivore extends Creature{
    public Herbivore(Coordinate coordinate, Map map) {
        super(coordinate, map);
        speed = 1;
        target = Grass.class;
    }

    @Override
    public void makeMove() {

    }
}
