package com.tychovonr.Orbital;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class OrbitalApp extends AbstractSimulation {
    double maxX = 0;
    Orbital orbital;
    Orbital orbital2;
    double maxY = 0;
    Circle circle = new Circle();
    Circle circle2 = new Circle();
    Trail vT = new Trail();
    PlotFrame plotFrame = new PlotFrame("x", "meters from ground", "Moving Ball");
    double totalTime;

    public void reset(){
        control.setValue("Starting Y position", 0);
        control.setValue("Starting X position", 0);
        control.setValue("Mass", 1000);
        plotFrame.clearDrawables();

    }
    public void initialize() {
        vT.clear();
        maxX = 0;
        maxY = 0;
        double startingY = control.getDouble("Starting Y position");
        double startingX = control.getDouble("Starting X position");
        double mass = control.getDouble("Mass");
        orbital = new Orbital(mass,startingY,startingX);
        orbital2 = new Orbital(1000,100,100);
        circle2.setX(100);
        circle2.setY(100);
        circle.setX(startingX);
        circle.setY(startingY);
        circle.color = Color.BLUE;
        circle2.color = Color.RED;

        plotFrame.addDrawable(circle);
        plotFrame.addDrawable(circle2);
        plotFrame.addDrawable(vT);
        plotFrame.setSize(800,800);
        plotFrame.setPreferredMinMax(0,4,0,4);
        vT.addPoint(0,startingY);
        plotFrame.setDefaultCloseOperation(3);
        plotFrame.setVisible(true);
    }
    public void doStep() {
        for (int i = 0; i < (int) (.1/orbital.getdT()); i++) {
            orbital.Step(orbital2);
            orbital2.Step(orbital);
        }

        circle.setX(orbital.getX());
        circle.setY(orbital.getY());
        circle2.setX(orbital2.getX());
        circle2.setY(orbital2.getY());
        plotFrame.setPreferredMinMax(0,100+maxX+maxY,0,100+maxY+maxX);
        vT.addPoint(orbital.getX(), orbital.getY());
        totalTime++;

    }
    public void stop(){
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new OrbitalApp());
    }
}
