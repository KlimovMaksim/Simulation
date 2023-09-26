package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;

abstract public class Creature extends Entity{
    public final int maxHp;
    protected int curHp;

    public Creature(Coordinate coordinate, Symbols symbol, int maxHp) {
        super(coordinate, symbol);
        this.maxHp = maxHp;
        this.curHp = maxHp;
    }

    abstract protected void makeMove(CreaturesMoves creaturesMove);

    public int getCurHp() {
        return curHp;
    }

    public void setCurHp(int curHp) {
        this.curHp = curHp;
    }
}
