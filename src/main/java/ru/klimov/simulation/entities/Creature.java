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
    protected Class target;
    protected BreadthFirstSearch breadthFirstSearch;

    public Creature(Coordinate coordinate) {
        super(coordinate);
    }

    public abstract void makeMove();

    public Class getTarget() {
        return target;
    }

    public Stack<Coordinate> findWayToTarget(Map map){
        breadthFirstSearch = new BreadthFirstSearch(this, map);
        return breadthFirstSearch.startSearchEngine();
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

