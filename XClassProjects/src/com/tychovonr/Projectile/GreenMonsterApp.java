package com.tychovonr.Projectile;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.display2d.GridTableModel;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class GreenMonsterApp extends AbstractSimulation{
    Particle parti;
    Double vel;
    Circle circle = new Circle();
    Trail vT = new Trail();
    Trail greenMonster = new Trail();
    PlotFrame plotFrame = new PlotFrame("x", "meters from ground", "Green Monster");
    double totalTime;

    public void reset(){
        control.setValue("Green Monster Distance", 100);
        control.setValue("Green Monster Height", 10);
        control.setValue("Starting Velocity", 10);
        control.setValue("Gravity", -9.81);
        control.setValue("Drag", 0.01);
        control.setValue("Mass", 1);
        control.setValue("Delta Time", .001);
        plotFrame.clearDrawables();

    }
    public void initialize() {
        greenMonster.clear();
        vT.clear();
        double GMDis = control.getDouble("Green Monster Distance");
        double GMHei = control.getDouble("Green Monster Height");
        double startVel = control.getDouble("Starting Velocity");
        double gravity = control.getDouble("Gravity");
        double drag = control.getDouble("Drag");
        double mass = control.getDouble("Mass");
        double deltaTime = control.getDouble("Delta Time");
        vel = startVel;
        parti = new Particle(startVel,gravity,drag,mass,0,deltaTime);
        circle.setX(parti.x);
        circle.setY(parti.y);

        greenMonster.color = Color.green;
        plotFrame.addDrawable(greenMonster);
        greenMonster.addPoint(GMDis,0);
        greenMonster.addPoint(GMDis,GMHei);
        plotFrame.addDrawable(circle);
        plotFrame.addDrawable(vT);
        plotFrame.setSize(800,800);
        plotFrame.setPreferredMinMax(0,GMDis*5/4,0,GMDis*5/4);

       vT.addPoint(parti.x,parti.y);
       plotFrame.setDefaultCloseOperation(3);
        plotFrame.setVisible(true);
        double[] mins = findMin(GMDis,GMHei,startVel,parti);
        parti.velX = mins[0]*Math.cos(mins[1]);
        parti.velY = mins[0]*Math.sin(mins[1]);;
        parti.oldVelY = 0;
        parti.oldVelX = 0;
        parti.x = 0;
        parti.y = 0;
        System.out.println("Min Vel = " + mins[0] + ", Min Angle = " + Math.toDegrees(mins[1]));


    }
    public void doStep() {
        for (int i = 0; i < (int) (.1/parti.dT); i++) {
            parti.squareStep();
        }

        circle.setX(parti.getX());
        circle.setY(parti.getY());
        vT.addPoint(parti.x,parti.y);
    }
    public double[] findMin(double GMDis, double GMHei, double startVel, Particle parti){
        double[] mins = new double[2];
        while (parti.x <GMDis || parti.y <GMHei){
            startVel = startVel + 1;
            if (startVel>2000){
                System.out.println("stop");
            }
            for (double theta = 1; theta <90; theta++){
                parti.x = 0;
                parti.y = 0;
                parti.oldVelX = 0;
                parti.oldVelY = 0;
                parti.accX = 0;
                parti.accY = 0;
                parti.theta = Math.toRadians(theta);
                parti.velX = startVel*Math.cos(Math.toRadians(theta));
                parti.velY = startVel*Math.sin(Math.toRadians(theta));
                parti.v = startVel;
                parti.t = 0;
                while (parti.x < GMDis && parti.y > -10){
                    parti.squareStep();
                }
                if (parti.y > GMHei){
                    mins[0] = startVel;
                    mins[1] = parti.theta;
                    return mins;
                }
            }
        }
        parti.x = 0;
        parti.y = 0;
        return mins;
    }
    public void stop(){
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new GreenMonsterApp());
    }
}
