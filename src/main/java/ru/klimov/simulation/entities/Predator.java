package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;

public class Predator extends Creature{
    public final int damage;

    public Predator(Coordinate coordinate, Symbols symbol, int maxHp, int damage) {
        super(coordinate, symbol, maxHp);
        this.damage = damage;
    }

    // дозаполлнить
    @Override
    protected void makeMove(CreaturesMoves creaturesMove) {

    }
}
