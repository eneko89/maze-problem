package es.deusto.ingenieria.maze;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.heuristic.BestFS;
import es.deusto.ingenieria.maze.Cell.Foot;
import es.deusto.ingenieria.maze.Cell.Wall;
import gail.grid.Grid;
import gail.grid.Grid.StrokeLocation;
import gail.grid.GridElement;
import gail.grid.Resources;
import gail.grid.Resources.Robot;
import gail.grid.animations.*;
import gail.grid.executors.ActionSequence;
import gail.utils.PresentationScreen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    public static void createAndShowGUI(final Environment env,
                                        final List<String> operators) {
        
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

        // Create a GridElement and define actions on them.
        // Each action needs an instance of Animation.
        final GridElement robot = new GridElement(Resources.getRobot(Robot.BLACK));
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
                if (cells[i][j].hasWalls()) {
                    ArrayList<Wall> walls = cells[i][j].getWalls();
                    for(Wall w : walls) {
                        StrokeLocation strokeLocation = null;
                        switch (w) {
                            case LEFT:
                                strokeLocation = StrokeLocation.LEFT;
                                break;
                            case RIGHT:
                                strokeLocation = StrokeLocation.RIGHT;
                                break;
                            case TOP:
                                strokeLocation = StrokeLocation.TOP;
                                break;
                            case BOTTOM:
                                strokeLocation = StrokeLocation.BOTTOM;
                                break;
                        }
                        grid.addOverlayStroke(10, new Point(i,j), strokeLocation);
                    }
                }
            }
        }

        // Darker backgrounds for the start and final positions
        // with two elements.
        GridElement darkBg1 = new GridElement();
        GridElement darkBg2 = new GridElement();
        darkBg1.setBackground(Color.lightGray);
        darkBg2.setBackground(Color.lightGray);
        darkBg1.setOpaque(true);
        darkBg2.setOpaque(true);
        grid.add(darkBg1, env.getStartLocation());
        grid.add(darkBg2, env.getEndLocation());
        
        // Create a presentation screen :)
        BufferedImage logo = Resources.loadImageResource("/es/deusto/ingenieria/"
                                                          + "maze/mazep.png");
        final PresentationScreen ps = new PresentationScreen("CLICK TO START!",
                                                              logo);
        ps.attach(frame);
        ps.showScreen();
        // Don't forget to make the frame visible!
        frame.setVisible(true);
        
        // Actions will be executed after the PresentationScreen is clicked and
        // hiden.
        ps.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ps.hideScreen();
                // Create an ActionSequence to execute actions on our element (the
                // robot) sequentially, with an initial delay of 500ms.
                ActionSequence robotActions = new ActionSequence(500);
                for(String operator : operators) {
                    robotActions.execute(robot, operator);
                }
            }
        });
        


    }

}
