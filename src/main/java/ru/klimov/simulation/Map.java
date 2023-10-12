package ru.klimov.simulation;

import ru.klimov.simulation.entities.*;

import java.util.HashMap;

public class Map {
    public final int maximumRows = 20;
    public final int maximumColumns = 20;
    HashMap<Coordinate, Entity> entities  = new HashMap<>();

    public void setEntity(Coordinate coordinate, Entity entity){
        entity.coordinate = coordinate;
        entities.put(coordinate, entity);
    }

    public Entity getEntity(Coordinate coordinate){
        return entities.get(coordinate);
    }

    public Entity getEntity(int row, int column){
        return getEntity(new Coordinate(row, column));
    }

    public boolean isCellEmpty(Coordinate coordinate){
        return !entities.containsKey(coordinate);
    }

    public void removeEntity(Coordinate coordinate){
        entities.remove(coordinate);
    }

    public void moveEntity(Coordinate from, Coordinate to){
        Entity entity = getEntity(from);
        removeEntity(from);
        setEntity(to, entity);
    }

    public void setupDefaultEntitiesPositions(){
        setEntity(new Coordinate(2, 7), new Herbivore(new Coordinate(2, 7)));
        setEntity(new Coordinate(6, 11), new Grass(new Coordinate(6, 11)));
        setEntity(new Coordinate(4, 15), new Predator(new Coordinate(4, 15)));
        setEntity(new Coordinate(15, 9), new Rock(new Coordinate(15, 9)));

        setEntity(new Coordinate(3, 7), new Rock(new Coordinate(3, 7)));
        setEntity(new Coordinate(3, 8), new Rock(new Coordinate(3, 8)));
        setEntity(new Coordinate(2, 8), new Rock(new Coordinate(2, 8)));

        setEntity(new Coordinate(1, 2), new Tree(new Coordinate(1, 2)));
        setEntity(new Coordinate(17, 2), new Tree(new Coordinate(17, 2)));
        setEntity(new Coordinate(2, 1), new Rock(new Coordinate(2, 1)));
        setEntity(new Coordinate(2, 2), new Rock(new Coordinate(2, 2)));
        setEntity(new Coordinate(2, 3), new Rock(new Coordinate(2, 3)));
    }
}
