package es.deusto.ingenieria.maze;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.heuristic.BestFS;
import java.util.List;

public class MainProgram {

    /**
     * @param args
     */
    public static void main(String[] args) {
        MazeProblem problem = new MazeProblem();
        EnvironmentXMLReader SAXParser = new EnvironmentXMLReader("data/feetmaze-1.xml"); 					
        State initialState = new State(SAXParser.getStartEnvironment());
        State finalState = new State(SAXParser.getFinalEnvironment());
        problem.addInitialState(initialState);
        // Following line its not neccesary because isFinalState() is overriden
        problem.addFinalState(finalState);
        List<String> operators = problem.solve(new BestFS(new MazeEvalFunction()));
        if (operators != null) {
            System.out.println("Solution found!!");
            for(String str : operators) {
                System.out.println(str);
            }
        } else {
            System.out.println("Unable to find the solution!");
        }
    }

}
