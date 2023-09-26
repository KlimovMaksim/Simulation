package ru.klimov.simulation;

import ru.klimov.simulation.entities.*;

import java.util.HashMap;

public class Map {
    HashMap<Coordinate, Entity> entities  = new HashMap<>();

    public void setEntity(Coordinate coordinate, Entity entity){
        entity.coordinate = coordinate;
        entities.put(coordinate, entity);
    }

    public Entity getEntity(Coordinate coordinate){
        return entities.get(coordinate);
    }

    public boolean isCellEmpty(Coordinate coordinate){
        return !entities.containsKey(coordinate);
    }

    public void setupDefaultEntitiesPositions(){
        setEntity(new Coordinate(2, 7), new Herbivore(new Coordinate(2, 7)));
        setEntity(new Coordinate(4, 15), new Predator(new Coordinate(4, 15)));
        setEntity(new Coordinate(15, 9), new Rock(new Coordinate(15, 9)));
        setEntity(new Coordinate(10, 3), new Grass(new Coordinate(10, 3)));
        setEntity(new Coordinate(12, 7), new Tree(new Coordinate(12, 7)));
        setEntity(new Coordinate(17, 2), new Tree(new Coordinate(17, 2)));
    }
}
