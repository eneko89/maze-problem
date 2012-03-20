package es.deusto.ingenieria.maze;

import java.awt.Point;

public class Environment {
    
    private Cell[][] cells;
    private Point startLocation;
    private Point endLocation;
    private Point currentLocation;
    
    /**
     * Constructor to be used the first time the enviroment is created. The
     * current location will be equal to the start location.
     */
    public Environment(Cell[][] cells, Point startLocation, Point endLocation) {
        this.cells = cells;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.currentLocation = startLocation;
    }
    
    private Environment(Cell[][] cells, Point startLocation,
                        Point endLocation, Point currentLocation) {
        this.cells = cells;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.currentLocation = currentLocation;
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
    
    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
    
}
