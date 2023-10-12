package ru.klimov.simulation.entities;

import ru.klimov.simulation.BreadthFirstSearch;
import ru.klimov.simulation.Coordinate;
import ru.klimov.simulation.ShiftCoordinate;
import ru.klimov.simulation.Map;

import java.util.*;

public abstract class Creature extends Entity {
    public final int maximumHealPoints = 10;
    protected int currentHealPoints = maximumHealPoints;
    protected int speed;
    protected Class targetEntity;
    protected BreadthFirstSearch breadthFirstSearch;

    public Creature(Coordinate coordinate) {
        super(coordinate);
    }

    public abstract void makeMove();

    public Class getTargetEntity() {
        return targetEntity;
    }

    public Stack<Coordinate> findWayToTarget(Map map){
        breadthFirstSearch = new BreadthFirstSearch(this, map);
        return breadthFirstSearch.startSearchEngine();
    }

    public boolean isTargetNear(Map map, Coordinate selectedCoordinate){
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                // is current coordinate equals selected one
                if ((r == 0) && (c == 0)){
                    continue;
                }
                Entity possibleTarget = map.getEntity(new Coordinate(
                        selectedCoordinate.row + r,
                        selectedCoordinate.column + c
                ));
                if (possibleTarget == null) continue;
                if (possibleTarget.getClass() == targetEntity){
                    return true;
                }
            }
        }
        return false;
    }

    public Set<Coordinate> getAvailableMoves(Map map, Coordinate selectedCoordinate) {
        Set<Coordinate> result = new HashSet<>();
        for (ShiftCoordinate shift: getEntityMoves()) {
            if (selectedCoordinate.canShift(shift, map)){
                Coordinate newCoordinate = selectedCoordinate.convertShiftToCoordinate(shift);

                if (map.isCellEmpty(newCoordinate)) result.add(newCoordinate);
            }
        }
        return result;
    }

    private Set<ShiftCoordinate> getEntityMoves(){
        return new HashSet<>(Arrays.asList(
                new ShiftCoordinate(speed, speed),
                new ShiftCoordinate(0, speed),
                new ShiftCoordinate(-speed, speed),
                new ShiftCoordinate(-speed, 0),
                new ShiftCoordinate(-speed, -speed),
                new ShiftCoordinate(0, -speed),
                new ShiftCoordinate(speed, -speed),
                new ShiftCoordinate(speed, 0)
        ));
    }
}

