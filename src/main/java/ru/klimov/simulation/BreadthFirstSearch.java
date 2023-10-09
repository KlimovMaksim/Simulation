package ru.klimov.simulation;

import ru.klimov.simulation.entities.Creature;
import ru.klimov.simulation.entities.Entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class BreadthFirstSearch {
    private LinkedList<Coordinate> searchQueueCoordinates = new LinkedList<>();
    private HashSet<Coordinate> visitedCoordinates = new HashSet<>();
    private HashMap<Coordinate, Coordinate> callTable = new HashMap<>(); // key - to; value - from
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;
    private Coordinate selectedCoordinate;
    private Creature selectedCreature;
    private Class targetEntity;
    private Map gameMap;

    public BreadthFirstSearch(Creature creature, Map gameMap) {
        this.startCoordinate = creature.coordinate;
        this.endCoordinate = null;
        this.selectedCoordinate = null;
        this.selectedCreature = creature;
        this.targetEntity = creature.getTarget();
        this.gameMap = gameMap;
    }

    private boolean isTargetNear(){
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                // is current coordinate equals selected one
                if ((r == 0) && (c == 0)){
                    continue;
                }
                Entity possibleTarget = gameMap.getEntity(new Coordinate(
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

    private Stack<Coordinate> reconstructWayToTarget() {
        Stack<Coordinate> pathToTarget = new Stack<>();
        Coordinate temp = callTable.get(endCoordinate);
        pathToTarget.push(endCoordinate);
        while (!temp.equals(startCoordinate)) {
            pathToTarget.push(temp);
            temp = callTable.get(temp);
        }
        return pathToTarget;
    }

    private void updateDataStructures(Coordinate coordinate){
        for (Coordinate c:
                selectedCreature.getAvailableMoves(gameMap, startCoordinate)) {
            searchQueueCoordinates.offerLast(c);
            callTable.putIfAbsent(c, coordinate);
        }
        visitedCoordinates.add(coordinate);
    }

    public Stack<Coordinate> findWayToTarget(){
        //LinkedList<Coordinate> searchQueueCoordinates = new LinkedList<>();
        // HashSet<Coordinate> visitedCoordinates = new HashSet<>();
        // HashMap<Coordinate, Coordinate> callTable = new HashMap<>(); // key - to; value - from
        // Stack<Coordinate> pathToTarget;
        // Coordinate startCoordinate = coordinate;
        // Coordinate endCoordinate = null;

        //for (Coordinate c:
        //         selectedCreature.getAvailableMoves(gameMap, startCoordinate)) {
        //     searchQueueCoordinates.offerLast(c);
        //     callTable.putIfAbsent(c, startCoordinate);
        // }
        // visitedCoordinates.add(startCoordinate);
        updateDataStructures(startCoordinate);
        while (!searchQueueCoordinates.isEmpty()){
            selectedCoordinate = searchQueueCoordinates.pollFirst();
            if (!visitedCoordinates.contains(selectedCoordinate)){
                if (isTargetNear()){
                    endCoordinate = selectedCoordinate;
                    break;
                }
                else {
                    //for (Coordinate c:
                    //         selectedCreature.getAvailableMoves(gameMap, selectedCoordinate)) {
                    //     searchQueueCoordinates.offerLast(c);
                    //     callTable.putIfAbsent(c, selectedCoordinate);
                    // }
                    // visitedCoordinates.add(selectedCoordinate);
                    updateDataStructures(selectedCoordinate);
                }
            }
        }
        return reconstructWayToTarget();
    }
}
