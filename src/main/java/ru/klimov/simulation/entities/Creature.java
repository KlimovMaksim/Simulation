package ru.klimov.simulation.entities;

import ru.klimov.simulation.Coordinate;
import ru.klimov.simulation.ShiftCoordinate;
import ru.klimov.simulation.Map;

import java.util.*;

public abstract class Creature extends Entity {
    public final int maximumHealPoints = 10;
    protected int currentHealPoints = maximumHealPoints;
    protected int speed;
    protected Class target;

    public Creature(Coordinate coordinate) {
        super(coordinate);
    }

    public abstract void makeMove();

    protected void findWayToTarget(Map map){
        LinkedList<Coordinate> searchQueueCoordinates = new LinkedList<>();
        HashSet<Coordinate> searchedCoordinates = new HashSet<>();
        HashMap<Coordinate, Coordinate> callTable = new HashMap<>(); // key - from; value - to

        for (Coordinate c: getAvailableMoves(map, coordinate)) {
            searchQueueCoordinates.offerLast(c);
            callTable.put(coordinate, c);
        }

        while (!searchQueueCoordinates.isEmpty()){
            Coordinate selectedCoordinate = searchQueueCoordinates.pollFirst();
            if (!searchedCoordinates.contains(selectedCoordinate)){
                if (isTargetNear(selectedCoordinate, map) != null){
                    // return
                }
                else {
                    searchedCoordinates.add(selectedCoordinate);
                    for (Coordinate c: getAvailableMoves(map, selectedCoordinate)) {
                        searchQueueCoordinates.offerLast(c);
                        callTable.put(selectedCoordinate, c);
                    }
                }
            }
        }
    }

    public Entity isTargetNear(Coordinate selectedCoordinate, Map map) {
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if ((r == 0) && (c == 0)){
                    continue;
                }

                Entity possibleTarget = map.getEntity(new Coordinate(
                        selectedCoordinate.row + r,
                        selectedCoordinate.column + c
                ));

                if (possibleTarget.getClass() == target){
                    return possibleTarget;
                }
            }
        }

        return null;
    }

    protected Set<Coordinate> getAvailableMoves(Map map, Coordinate selectedCoordinate) {
        Set<Coordinate> result = new HashSet<>();

        for (ShiftCoordinate shift: getEntityMoves()) {
            if (selectedCoordinate.canShift(shift, map)){
                Coordinate newCoordinate = selectedCoordinate.convertShiftToCoordinate(shift);

                if (map.isCellEmpty(newCoordinate)) result.add(newCoordinate);
            }
        }

        return result;
    }

    // make not abstract
    protected abstract Set<ShiftCoordinate> getEntityMoves();
}

