package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;

public class Herbivore extends Creature{
    public Herbivore(Coordinate coordinate, Symbols symbol, int maxHp) {
        super(coordinate, symbol, maxHp);
    }

    // дозаполнить
    @Override
    protected void makeMove(CreaturesMoves creaturesMove) {

    }
}
