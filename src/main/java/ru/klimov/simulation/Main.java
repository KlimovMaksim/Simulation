package ru.klimov.simulation;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        map.setupDefaultEntitiesPositions();

        MapRenderer mapRenderer = new MapRenderer();
        mapRenderer.render(map);

        int a = 123;
    }
}