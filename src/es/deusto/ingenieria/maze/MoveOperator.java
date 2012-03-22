package es.deusto.ingenieria.maze;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;
import java.awt.Point;

public class MoveOperator extends Operator {

    public enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
    private Direction direction;

    public MoveOperator(Direction moveDirection) {
        super("move" + moveDirection.toString());
        this.direction = moveDirection;
    }

    @Override
    protected State effect(State state) {
        Environment environment = (Environment) state.getInformation();
        Point currentLocation = environment.getCurrentLocation();
        switch(direction) {
            case UP:
                return 
                    new State(environment.derive(new Point(currentLocation.x,
                                                           currentLocation.y - 1)));
            case DOWN:
                return 
                    new State(environment.derive(new Point(currentLocation.x,
                                                           currentLocation.y + 1)));
            case LEFT:
                return
                    new State(environment.derive(new Point(currentLocation.x - 1,
                                                           currentLocation.y)));
            case RIGHT:
                return 
                    new State(environment.derive(new Point(currentLocation.x + 1,
                                                           currentLocation.y)));
            default:
                return null;
        }
    }

    @Override
    protected boolean isApplicable(State state) {
        Environment environment = (Environment) state.getInformation();
        Point currentLocation = environment.getCurrentLocation();
        switch(direction) {
            case UP:
                return currentLocation.y > 0;
            case DOWN:
                return currentLocation.y < environment.getRowCount() - 1 ;
            case LEFT:
                return currentLocation.x > 0;
            case RIGHT:
                return currentLocation.x < environment.getColumnCount() - 1;
            default:
                return false;
        }
    }

}
