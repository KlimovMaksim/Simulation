package ru.klimov.simulation;

import ru.klimov.simulation.entities.Entity;

public class MapRenderer {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private final String ANSI_BLACK_BACKGROUND = "\u001B[0;100m";
    private final String ANSI_RED_ENTITY_COLOR = "\u001B[1;91m";
    private final String ANSI_YELLOW_ENTITY_COLOR = "\u001B[1;93m";
    private final String ANSI_BLUE_ENTITY_COLOR = "\u001B[1;94m";
    private final String ANSI_PURPLE_ENTITY_COLOR = "\u001B[1;95m";
    private final String ANSI_WHITE_ENTITY_COLOR = "\u001B[1;97m";

    public void render(Map map){
        for (int row = 1; row <= map.maximumRows; row++){
            String line = "";
            for (int colomn = 1; colomn <= map.maximumColumns; colomn++) {
                Coordinate coordinate = new Coordinate(row, colomn);

                line += getSpriteForCell(map, coordinate);

            }

            System.out.println(line + ANSI_RESET);
        }
    }

    private String getSpriteForCell(Map map, Coordinate coordinate){
        if (map.isCellEmpty(coordinate)){
            return colorizeSpriteBackground("   ", isCellBlack(coordinate));
        }
        else {
            return colorizeSpriteBackground(
                    colorizeSpriteFont(map.getEntity(coordinate)),
                    isCellBlack(coordinate)
            );
        }
    }

    private String colorizeSpriteFont(Entity entity){
        String result = "";

        switch (entity.getClass().getSimpleName()){
            case "Grass":
                result = ANSI_YELLOW_ENTITY_COLOR + " G ";
                break;
            case "Herbivore":
                result = ANSI_WHITE_ENTITY_COLOR + " H ";
                break;
            case "Predator":
                result = ANSI_RED_ENTITY_COLOR + " P ";
                break;
            case "Rock":
                result = ANSI_PURPLE_ENTITY_COLOR + " R ";
                break;
            case "Tree":
                result = ANSI_BLUE_ENTITY_COLOR + " T ";
                break;
        }

        return result;
    }

    private String colorizeSpriteBackground(String sprite, boolean isDark){
        String result = sprite;

        if (isDark){
            result = ANSI_WHITE_BACKGROUND + result;
        }
        else{
            result = ANSI_BLACK_BACKGROUND + result;
        }

        return result;
    }

    private static boolean isCellBlack(Coordinate coordinate){
        return ((coordinate.row + coordinate.column) % 2) == 0;
    }
}
