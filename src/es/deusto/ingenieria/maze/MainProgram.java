package es.deusto.ingenieria.maze;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.heuristic.BestFS;
import es.deusto.ingenieria.maze.Cell.Foot;
import gail.grid.Grid;
import gail.grid.GridElement;
import gail.grid.Resources;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

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
            createAndShowGUI(initialEnvironment);
        } else {
            System.out.println("Unable to find the solution!");
        }
    }
    
    public static void createAndShowGUI(Environment env) {
        
        /* * * * * * * * * * *
         * Creating the GUI  *
         * * * * * * * * * * */

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(new Dimension(400, 200));

        // Create the grid.
        Grid grid = new Grid(env.getColumnCount(), env.getRowCount());
        grid.setBackground(Color.white);
        grid.setForeground(Color.lightGray);
        f.setContentPane(grid);
        f.setVisible(true);
        
        // Loading feet images
        BufferedImage leftFoot= Resources.loadImageResource(
                                        "/es/deusto/ingenieria/maze/left.png");
        BufferedImage rightFoot= Resources.loadImageResource(
                                        "/es/deusto/ingenieria/maze/right.png");
        
        // Add the feet elements.
        Cell[][] cells = env.getCells();
        int columns = env.getColumnCount();
        int rows = env.getRowCount();
        for(int i=0; i < columns; i++) {
            for (int j=0; j < rows; j++) {
                if (cells[i][j].getFoot() == Foot.LEFT)
                    grid.add(new GridElement(leftFoot), new Point(i,j));
                else
                    grid.add(new GridElement(rightFoot), new Point(i,j));
            }
        }
    }

}
