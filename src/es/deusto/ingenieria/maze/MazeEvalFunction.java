/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.deusto.ingenieria.maze;

import es.deusto.ingenieria.aike.search.Node;
import es.deusto.ingenieria.aike.search.heuristic.EvaluationFunction;
import java.awt.Point;

/**
 *
 * @author eneko
 */
public class MazeEvalFunction extends EvaluationFunction {

    @Override
    public double calculateG(Node node) {
        return node.getDepth();
    }

    @Override
    public double calculateH(Node node) {
        Environment environment = ((Environment)node.getState().getInformation());
        return calculateManhattanDist(environment.getCurrentLocation(),
                                      environment.getEndLocation());
    }
    
    private int calculateManhattanDist(Point a, Point b) {
        return -(Math.abs(b.x - a.x) + Math.abs(b.y - a.y));
    }
    
}
