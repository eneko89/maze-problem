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
    
    public List<String> solve(SearchMethod searchMethod) {
        Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));

        if (finalNode != null) {
            System.out.println("Solution found!!");
            List<String> operators = new ArrayList<String>();
            searchMethod.solutionPath(finalNode, operators);
            searchMethod.createSolutionLog(operators);
            return operators;
        } else {
            System.out.println("Unable to find the solution!");
            return null;
        }
    }

    @Override
    public boolean isFinalState(State state) {
        Environment environment = (Environment) state.getInformation();
        //System.out.println(environment.getCurrentLocation().x + " " + environment.getCurrentLocation().y);
        return environment.getCurrentLocation()
                                .equals(environment.getEndLocation());
    }

    
}
