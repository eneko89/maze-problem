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
        Environment initialEnvironment = SAXParser.getInformation();
        problem.addInitialState(new State(initialEnvironment));
        // We ommit adding a final state to the problem because we overrided
        // isFinalState().
        List<String> operators = problem.solve(new BestFS(new MazeEvalFunction()));
        if (operators != null) {
            System.out.println("Solution found!!");
            for(String str : operators) {
                System.out.println(str);
            }
            createAndShowGUI();
        } else {
            System.out.println("Unable to find the solution!");
        }
    }
    
    public static void createAndShowGUI() {
        
    }

}
