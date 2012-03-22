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
        State initialState = new State((Environment)SAXParser.getInformation());
        problem.addInitialState(initialState);		
        List<String> operators = problem.solve(new BestFS(new MazeEvalFunction()));
    }

}
