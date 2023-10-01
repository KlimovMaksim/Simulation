package ru.klimov.simulation;

public class Coordinate {
    public final int row;
    public final int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Coordinate shift(ShiftCoordinate shiftCoordinate){
        return new Coordinate(this.row + shiftCoordinate.shiftRow,
                this.column + shiftCoordinate.shiftColumn);
    }

    public boolean canShift(ShiftCoordinate shiftCoordinate, Map map){
        int row = this.row + shiftCoordinate.shiftRow;
        int column = this.column + shiftCoordinate.shiftColumn;

        if ((row < 1) || (row > map.maximumRows)) return false;
        if ((column < 1) || (column > map.maximumColumns)) return false;

        // is way clear?

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (row != that.row) return false;
        return column == that.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
