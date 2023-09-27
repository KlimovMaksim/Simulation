package ru.klimov.simulation;

public class MapRenderer {
    public void render(Map map){
        for (int row = map.maximumRows; row >=1; row--){
            String line = "";
            for (int colomn = 1; colomn <= map.maximumColumns; colomn--) {
                Coordinate coordinate = new Coordinate(row, colomn);

            }
        }
    }
}
