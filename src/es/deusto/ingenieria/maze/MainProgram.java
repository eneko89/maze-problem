package es.deusto.ingenieria.maze;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.heuristic.BestFS;
import es.deusto.ingenieria.maze.Cell.Foot;
import gail.grid.Grid;
import gail.grid.GridElement;
import gail.grid.Resources;
import gail.grid.Resources.Robot;
import gail.grid.animations.MoveDownAnimation;
import gail.grid.animations.MoveLeftAnimation;
import gail.grid.animations.MoveRightAnimation;
import gail.grid.animations.MoveUpAnimation;
import gail.grid.executors.ActionSequence;
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
            createAndShowGUI(initialEnvironment, operators);
        } else {
            System.out.println("Unable to find the solution!");
        }
    }
    
    /**
     * Creates the GUI.
     * 
     * @param env 
     *          the environment information, needed to build the GUI
     * @param operators
     *          string representation of the operators executed to reach the solution
     */
    public static void createAndShowGUI(Environment env,
                                        List<String> operators) {
        
        /* * * * * * * * * * *
         * Creating the GUI  *
         * * * * * * * * * * */

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 600));

        // Create the grid.
        Grid grid = new Grid(env.getColumnCount(), env.getRowCount());
        grid.setBackground(Color.white);
        grid.setForeground(Color.lightGray);
        frame.setContentPane(grid);
        frame.setVisible(true);

        // Create a GridElement and define actions on them.
        // Each action needs an instance of Animation.
        GridElement robot = new GridElement(Resources.getRobot(Robot.BLACK));
        robot.defineAction("moveRIGHT", new MoveRightAnimation());
        robot.defineAction("moveLEFT", new MoveLeftAnimation());
        robot.defineAction("moveUP", new MoveUpAnimation());
        robot.defineAction("moveDOWN", new MoveDownAnimation());

        // Add it to the start location
        // Z-order of the components goes like this: The fist element added
        // will be on top, and the last one on bottom.
        grid.add(robot, env.getStartLocation());
        
        // Load feet images
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

        // Create an ActionSequence to execute actions on our element (the
        // robot) sequentially, with an initial delay of 500ms.
        ActionSequence actions = new ActionSequence(500);
        for(String operator : operators) {
            actions.execute(robot, operator);
        }
        
    }

}
