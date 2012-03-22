/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.deusto.ingenieria.maze;

import java.util.ArrayList;

/**
 *
 * @author eneko
 */
public class Cell {
    
    public enum Wall {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }
    public enum Foot {
        LEFT,
        RIGHT
    }
    private ArrayList<Wall> walls; // No walls in the cell by default.
    private Foot foot = Foot.RIGHT; // By default cells foot is RIGHT.

    public void addWall(Wall wall) {
        if (walls == null)
            walls = new ArrayList<Wall>();
        walls.add(wall);
    }
    
    public void removeWall(Wall wall) {
        if (walls != null)
            walls.remove(wall);
    }

    public Foot getFoot() {
        return foot;
    }

    public void setFoot(Foot foot) {
        this.foot = foot;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        else
            return false;
    }
    
}
