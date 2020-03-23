import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;
import org.opensourcephysics.media.core.TCircle;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
/***
 * MovingBallApp is an extension of AbstractSimulation (an abstract class).
 *
 * An abstract class is a class that almost works except that the author intentionally left some methods empty. It is up
 * to the programmer who uses it to complete those empty methods for the class to fully work.
 *
 * In AbstractSimulation, these methods need to be completed:
 *     1. reset (adds options to the Control Panel)
 *     2. initialize (gets data from the Control Panel and sets up the graph)
 *     3. doStep (actions to take to do the animation, invoked every 1/10 second)
 *
 * You also need a main method to run the simulation (scroll to bottom).
 */
public class SpiralTrailApp extends AbstractSimulation {
    // Set up global variables.
    PlotFrame plotFrame = new PlotFrame("x", "meters from ground", "Moving Ball");
    int length = 5;//make length longer than width, since I don't want to put an if statement in later
    int width =5;
    int offset = 10;
    Circle[][] circle = new Circle[length][width];
    double totalTime;
    Random ran = new Random();
    /**
     * Technically optional, but the simulation won't work without it.
     * Adds options to the Control Panel.
     */
    @Override
    public void reset() {
        control.setValue("Starting Y position", 0);
        control.setValue("Starting X position", 0);
    }

    /**
     * Technically optional, but the simulation won't work without it.
     * Tied to the "Initialize" button. Gets information from the Control Panel and
     * configures the plot frame.
     */
    @Override
    public void initialize() {
        // Get information from the control panel.
        for (int i = 0; i <length ; i++) {
            for (int j = 0; j < width; j++) {
                circle[i][j] = new Circle();
                circle[i][j].setY(i);
                circle[i][j].setX(j);

                // Instead of appending x, y coordinates to plot frame,
                //    add the Circle which maintains its own x, y.
                plotFrame.addDrawable(circle[i][j]);

                // Configure plot frame
                plotFrame.setPreferredMinMax(0-offset, width+offset-1, 0-offset, length+offset-1); // Scale of graph.
                plotFrame.setDefaultCloseOperation(3); // Make it so x'ing out of the graph stops the program.
                plotFrame.setVisible(true); // Required to show plot frame.
            }

        }

    }
    public ArrayList<int[]> spiralMaker (int length, int width){
        ArrayList<int[]> spiralPoints = new ArrayList<int[]>();
        //int[] point = new int[2];
        int x;
        int y;
        if ((width)%2 == 1){
            x = width/2;
            y = width/2;
        }else{
            x = (width/2)-1;
            y = (width/2)-1;
        }
        int[] point = new int[]{x,y};
        spiralPoints.add(point);
        for (int i = 1; i <(width); i++) {

            if (i%2 == 0){
                for (int j = 0; j<(length-width)+i; j++) {
                    y--;
                    int[] newPoint = new int[]{x,y};
                    spiralPoints.add(newPoint);
                }
                x--;
                int[] newPoint = new int[]{x,y};
                spiralPoints.add(newPoint);
            }else {
                for (int j = 0; j<(length-width)+i; j++) {
                    y++;
                    int[] newPoint = new int[]{x,y};
                    spiralPoints.add(newPoint);
                }
                x++;
                int[] newPoint = new int[]{x,y};
                spiralPoints.add(newPoint);
            }


        }
        return spiralPoints;
    }

    /**
     * Required method, invoked once every 1/10 second until Stop is pressed.
     * The doStep method is also called when the Step button is pressed.
     */
    public void doStep() {

        Trail trail = new Trail();
        trail.color = Color.BLUE;
        plotFrame.addDrawable(trail);
        ArrayList<int[]> spiral = spiralMaker(length,width);
        for (int i = 0; i <spiral.size() ; i++) {
            trail.addPoint(spiral.get(i)[0], spiral.get(i)[1]);
        }
        totalTime++;
    }

    /**
     * Optional method, tied to Stop button.
     */
    @Override
    public void stop(){
        for (int i = 0; i <spiralMaker(length,width).size() ; i++) {
            System.out.println(spiralMaker(length,width).get(i)[0] + "," +spiralMaker(length,width).get(i)[1] );
        }

    }

    /**
     * Required main method, runs the simulation.
     * @param args
     */
    public static void main(String[] args) {
        SimulationControl.createApp(new SpiralTrailApp());
    }
}
