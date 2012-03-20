package es.deusto.ingenieria.maze;

import java.awt.Point;

public class Environment {
    
    private Cell[][] cells;
    private Point startLocation;
    private Point endLocation;
    
    public Environment(Cell[][] cells, Point startLocation, Point endLocation) {
        this.cells = cells;
    }
    
    public Cell getCellAt(int column, int row) {
        return cells[column][row];
    }
    
    public void setCellAt(int column, int row, Cell cell) {
        cells[column][row] = cell;
    }

    public Point getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Point endLocation) {
        this.endLocation = endLocation;
    }

    public Point getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Point startLocation) {
        this.startLocation = startLocation;
    }
    
}
