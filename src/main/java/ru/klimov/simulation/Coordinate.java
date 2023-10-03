package ru.klimov.simulation;

public class Coordinate {
    public final int row;
    public final int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Coordinate convertShiftToCoordinate(ShiftCoordinate shiftCoordinate){
        return new Coordinate(this.row + shiftCoordinate.shiftRow,
                this.column + shiftCoordinate.shiftColumn);
    }

    public boolean canShift(ShiftCoordinate shiftCoordinate, Map map){
        int row = this.row + shiftCoordinate.shiftRow;
        int column = this.column + shiftCoordinate.shiftColumn;

        if ((row < 1) || (row > map.maximumRows)) return false;
        if ((column < 1) || (column > map.maximumColumns)) return false;
        if (!isWayClear(shiftCoordinate, map)) return false;

        return true;
    }

    private boolean isWayClear(ShiftCoordinate shift, Map map){
        int rowAbsoluteValue = Math.abs(shift.shiftRow);
        int columnAbsoluteValue = Math.abs(shift.shiftColumn);
        int tempRow = shift.shiftRow;
        int tempColumn = shift.shiftColumn;

        while ((rowAbsoluteValue > 0) || (columnAbsoluteValue > 0)){

            if (!map.isCellEmpty(convertShiftToCoordinate(new ShiftCoordinate(tempRow, tempColumn)))){
                return false;
            }
            
            if (tempRow > 0){
                tempRow -= 1;
            } else if (tempRow < 0) {
                tempRow += 1;
            }
            if (tempColumn > 0){
                tempColumn -= 1;
            } else if (tempColumn < 0) {
                tempColumn += 1;
            }

            if (rowAbsoluteValue > 0) rowAbsoluteValue -= 1;
            if (columnAbsoluteValue > 0) columnAbsoluteValue -= 1;
        }

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
