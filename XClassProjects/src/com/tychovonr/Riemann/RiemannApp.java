package com.tychovonr.Riemann;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import com.tychovonr.Function.*;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.frames.PlotFrame;
import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
public class RiemannApp extends AbstractSimulation{
    public static void main(String[] args) {
        SimulationControl.createApp(new RiemannApp());
    }
    @Override
    public void reset() {
        control.println("Functions");
        control.println("0 for x^n");
        control.println("1 for (1)sin, (2)cos, (3)tan");
        control.println("2 for log");
        control.println("3 for  semicircle");
        control.println("Rules");
        control.println("0 for Right Hand Rule");
        control.println("1 for Left Hand Rule");
        control.println("2 for Trapezoid Rule");
        control.println("3 for Accumulation");
        control.setValue("Function",0);
        control.setValue("Rule", 0);
        control.setValue("Mod", 0);
        control.setValue("Domain", 20);
        control.setValue("Coeffecients", "1,2,3");

    }
    public void initialize() {
        PlotFrame plot = new PlotFrame("x","y","Riemann Sum");

        plot.setDefaultCloseOperation(3);
        plot.setVisible(true);
        plot.clearDrawables();
        control.println("Functions");
        control.println("0 for x^n");
        control.println("1 for (1)sin, (2)cos, (3)tan");
        control.println("2 for log");
        control.println("3 for  semicircle");
        control.println("Rules");
        control.println("0 for Right Hand Rule");
        control.println("1 for Left Hand Rule");
        control.println("2 for Trapezoid Rule");
        control.println("3 for Accumulation");
        int function = control.getInt("Function");
        int rule = control.getInt("Rule");
        int mod = control.getInt("Mod");
        String coefS = control.getString("Coeffecients");
        int domain = control.getInt("Domain");

        String[] letters =coefS.split(",");
        int[] values =new int[letters.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = Integer.parseInt(letters[i]);
        }
        Function fun;
        if (function == 0){
             fun = new PolyFunction();
        } else if(function == 1){
             fun = new SineFunction();
        } else if (function == 2){
             fun = new LogFunction();
        }else if(function == 3){
             fun = new SemiCircle();
        }else {
             fun = new PolyFunction();
        }
        if (rule == 0){
            RightHandRule rightHandRule = new RightHandRule();
            rightHandRule.rsPlot​(plot,fun,-domain/2,domain/2,100,mod,values);
            plot.setPreferredMinMax(-domain/2,domain/2,fun.min(mod,domain,values,fun),fun.max(mod,domain,values,fun));
        } else if(rule == 1){
            LeftHandRule leftHandRule = new LeftHandRule();
            leftHandRule.rsPlot​(plot,fun,-domain/2,domain/2,100,mod,values);
            plot.setPreferredMinMax(-domain/2,domain/2,fun.min(mod,domain,values,fun),fun.max(mod,domain,values,fun));
        } else if (rule == 2){
            TrapazoidRule trapazoidRule = new TrapazoidRule();
            trapazoidRule.rsPlot​(plot,fun,-domain/2,domain/2,100,mod,values);
            plot.setPreferredMinMax(-domain/2,domain/2,fun.min(mod,domain,values,fun),fun.max(mod,domain,values,fun));
        }else if(rule == 3){
            RightHandRule rightHandRule = new RightHandRule();
            rightHandRule.rsAcc​(plot,fun,domain,0, mod,values);
            plot.setPreferredMinMax(-domain/2,domain/2,-fun.max(mod,domain,values,fun),fun.max(mod,domain,values,fun));
        }

    }
    @Override
    protected void doStep() {

    }

}
