package com.tychovonr.Projectile;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class ProjectileApp extends AbstractSimulation {
    double maxX = 0;
    double testY;
    Particle parti;
    Particle partii;
    double maxY = 0;
    Circle circle = new Circle();
    Circle ciircle = new Circle();
    Trail vT = new Trail();
    Trail viT = new Trail();
    Trail greenMonster = new Trail();
    Trail graphX = new Trail();
    PlotFrame plotFrame = new PlotFrame("x", "meters from ground", "Moving Ball");
    PlotFrame velTime = new PlotFrame("Time", "Velocity", "Velocity vs Time");
    double totalTime;

    public void reset(){
        control.setValue("Starting Y position", 1);
        control.setValue("Starting X position", 0);
        control.setValue("Starting velocity", 50);
        control.setValue("Starting Y acceleration", 0);
        control.setValue("Starting X acceleration", 0);
        control.setValue("Gravity", -9.81);
        control.setValue("Drag", 0.01);
        control.setValue("Mass", 1);
        control.setValue("Theta", 45);
        control.setValue("Delta Time", .025);
        plotFrame.clearDrawables();

    }
    public void initialize() {
        vT.clear();
        maxX = 0;
        maxY = 0;
        double startingY = control.getDouble("Starting Y position");
        double startingX = control.getDouble("Starting X position");
        double startVel = control.getDouble("Starting velocity");
        double startYAcc = control.getDouble("Starting Y acceleration");
        double startXAcc = control.getDouble("Starting X acceleration");
        double gravity = control.getDouble("Gravity");
        double drag = control.getDouble("Drag");
        double mass = control.getDouble("Mass");
        double theta = control.getDouble("Theta");
        double deltaTime = control.getDouble("Delta Time");
        parti = new Particle(startingX,startingY,startVel,startXAcc,startYAcc,gravity,theta,drag,mass,deltaTime);
        partii = new Particle(startingX,startingY,startVel,startXAcc,startYAcc,gravity,theta,drag,mass,2*deltaTime);
        parti.oldVelX = parti.velX;
        circle.setX(startingX);
        circle.setY(startingY);
        ciircle.setX(startingX);
        ciircle.setY(startingY);
        ciircle.color = Color.BLUE;
        viT.color = Color.CYAN;

        graphX.color = Color.MAGENTA;
        greenMonster.color = Color.green;
        plotFrame.addDrawable(greenMonster);
        plotFrame.addDrawable(viT);
        greenMonster.addPoint(100,0);
        greenMonster.addPoint(100,10);
        plotFrame.addDrawable(circle);
        plotFrame.addDrawable(ciircle);
        plotFrame.addDrawable(vT);
        velTime.addDrawable(graphX);
        plotFrame.setSize(800,800);
        velTime.setSize(800,800);
        plotFrame.setPreferredMinMax(0,4,0,4);
        velTime.setPreferredMinMax(0,20,-1000,1000);
        vT.addPoint(0,startingY);
        viT.addPoint(0,startingY);
        plotFrame.setDefaultCloseOperation(3);
        plotFrame.setVisible(true);
        velTime.setVisible(true);
    }
    public void doStep() {
        for (int i = 0; i < (int) (.1/parti.dT); i++) {
            parti.squareStep();
        }
        circle.setX(parti.getX());
        circle.setY(parti.getY());
        ciircle.setX(partii.getX());
        ciircle.setY(partii.getY());
        plotFrame.setPreferredMinMax(0,100+maxX+maxY,0,100+maxY+maxX);
        vT.addPoint(parti.getX(), parti.getY());
        viT.addPoint(partii.getX(), partii.getY());
        graphX.addPoint(parti.getT(),parti.velX);
        totalTime++;

    }
    public void stop(){
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new ProjectileApp());
    }
}
