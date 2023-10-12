package ru.klimov.simulation;

import ru.klimov.simulation.entities.Creature;

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
        this.targetEntity = creature.getTargetEntity();
        this.gameMap = gameMap;
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
                selectedCreature.getAvailableMoves(gameMap, coordinate)) {
            searchQueueCoordinates.offerLast(c);
            callTable.putIfAbsent(c, coordinate);
        }
        visitedCoordinates.add(coordinate);
    }

    public Stack<Coordinate> startSearchEngine(){
        updateDataStructures(startCoordinate);
        while (!searchQueueCoordinates.isEmpty()){
            selectedCoordinate = searchQueueCoordinates.pollFirst();
            if (!visitedCoordinates.contains(selectedCoordinate)){
                if (selectedCreature.isTargetNear(gameMap, selectedCoordinate)){
                    endCoordinate = selectedCoordinate;
                    break;
                }
                else {
                    updateDataStructures(selectedCoordinate);
                }
            }
        }
        return reconstructWayToTarget();
    }
}
