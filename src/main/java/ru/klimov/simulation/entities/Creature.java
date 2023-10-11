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

    // TODO: make method smaller
    public Stack<Coordinate> findWayToTarget(Map map){
        // TODO: make initialize method
        LinkedList<Coordinate> searchQueueCoordinates = new LinkedList<>();
        HashSet<Coordinate> visitedCoordinates = new HashSet<>();
        HashMap<Coordinate, Coordinate> callTable = new HashMap<>(); // key - to; value - from
        Stack<Coordinate> pathToTarget;
        Coordinate startCoordinate = coordinate;
        Coordinate endCoordinate = null;

        // TODO: delegate to separate method
        for (Coordinate c: getAvailableMoves(map, coordinate)) {
            searchQueueCoordinates.offerLast(c);
            callTable.putIfAbsent(c, coordinate);
        }
        visitedCoordinates.add(coordinate);

        while (!searchQueueCoordinates.isEmpty()){
            Coordinate selectedCoordinate = searchQueueCoordinates.pollFirst();

            if (!visitedCoordinates.contains(selectedCoordinate)){
                if (isTargetNear(selectedCoordinate, map)){
                    endCoordinate = selectedCoordinate;
                    break;
                }
                else {
                    // TODO: delegate to separate method
                    for (Coordinate c: getAvailableMoves(map, selectedCoordinate)) {
                        searchQueueCoordinates.offerLast(c);
                        callTable.putIfAbsent(c, selectedCoordinate);
                    }
                    visitedCoordinates.add(selectedCoordinate);
                }
            }
        }

        pathToTarget = reconstructWayToTarget(callTable, startCoordinate, endCoordinate);
        return pathToTarget;
    }

    // TODO: make less arguments
    private Stack<Coordinate> reconstructWayToTarget(HashMap<Coordinate, Coordinate> callTable,
                                                    Coordinate start,
                                                    Coordinate end) {
        Stack<Coordinate> pathToTarget = new Stack<>();

        // TODO: try to use do-while
        Coordinate temp = callTable.get(end);
        pathToTarget.push(end);

        while (!temp.equals(start)) {
            pathToTarget.push(temp);
            temp = callTable.get(temp);
        }

        return pathToTarget;
    }


    private boolean isTargetNear(Coordinate selectedCoordinate, Map map) {
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if ((r == 0) && (c == 0)){
                    continue;
                }

                Entity possibleTarget = map.getEntity(new Coordinate(
                        selectedCoordinate.row + r,
                        selectedCoordinate.column + c
                ));
                if (possibleTarget == null) continue;
                if (possibleTarget.getClass() == target){
                    return true;
                }
            }
        }

        return false;
    }

    private Set<Coordinate> getAvailableMoves(Map map, Coordinate selectedCoordinate) {
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

