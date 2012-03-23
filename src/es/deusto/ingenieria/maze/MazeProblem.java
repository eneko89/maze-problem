package es.deusto.ingenieria.maze;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.Problem;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.Node;
import es.deusto.ingenieria.aike.search.SearchMethod;
import es.deusto.ingenieria.maze.MoveOperator.Direction;
import java.util.ArrayList;
import java.util.List;

public class MazeProblem extends Problem {

    public MazeProblem() {
        createOperators();
    }

    @Override
    protected void createOperators() {
        Operator opMoveUp = new MoveOperator(Direction.UP);
        this.addOperator(opMoveUp);
        Operator opMoveDown = new MoveOperator(Direction.DOWN);
        this.addOperator(opMoveDown);
        Operator opMoveLeft = new MoveOperator(Direction.LEFT);
        this.addOperator(opMoveLeft);
        Operator opMoveRight = new MoveOperator(Direction.RIGHT);
        this.addOperator(opMoveRight);
    }
    
    /**
     * Returns the list of the operators in string format if it finds a
     * solution and null if it is unable to find one.
     * 
     * @param searchMethod
     * @return list of the string representation of the operators
     */
    public List<String> solve(SearchMethod searchMethod) {
        Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
        if (finalNode != null) {
            List<String> operators = new ArrayList<String>();
            searchMethod.solutionPath(finalNode, operators);
            searchMethod.createSolutionLog(operators);
            return operators;
        } else
            return null;
    }

    @Override
    public boolean isFinalState(State state) {
        Environment environment = (Environment) state.getInformation();
        return environment.getCurrentLocation()
                                .equals(environment.getEndLocation());
    }

    
}
