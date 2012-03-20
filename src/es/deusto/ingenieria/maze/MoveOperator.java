package es.deusto.ingenieria.maze;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;
import java.awt.Point;

public class MoveOperator extends Operator {

    public enum Direction {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }
    private Direction direction;

    public MoveOperator(Direction moveDirection) {
        super("move" + moveDirection.toString());
        this.direction = moveDirection;
    }

    @Override
    protected State effect(State state) {
        Environment environment = (Environment) state.getInformation();
        Point newLocation = environment.getCurrentLocation();
        // TODO move to the new location.
        switch(direction) {
            case BOTTOM:
                break;
            case TOP:
                break;
            case LEFT:
                break;
            case RIGHT:
                break;
        }
        return null;
    }

    @Override
    protected boolean isApplicable(State state) {
            Environment environment = (Environment) state.getInformation();
            // TODO return true when the operator is applicable and false if not.
            return false;
    }

}
