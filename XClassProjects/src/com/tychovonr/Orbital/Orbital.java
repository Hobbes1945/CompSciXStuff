package com.tychovonr.Orbital;

import com.tychovonr.Matrix.Matrix;

import java.util.ArrayList;

public class Orbital {
    double m;
    double x;
    double y;
    double G;
    double dT;
    double v;
    double acc;
    double ang;
    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getX() {
        return x;
    }

    public void setX(double xPos) {
        this.x = xPos;
    }

    public double getY() {
        return y;
    }

    public void setY(double yPos) {
        this.y = yPos;
    }

    public double getG() {
        return G;
    }

    public void setG(double g) {
        G = g;
    }

    public double getdT() {
        return dT;
    }

    public void setdT(double dT) {
        this.dT = dT;
    }

    public Orbital(double m, double xPos, double yPos){
        this.m = m;
        this.x = xPos;
        this.y = yPos;
        this.G = 6.673E-11; // I won't let this be changed yet
    }
    public void Step(Orbital p2){
        ang = Math.asin((p2.getX()-this.getX())/(p2.getY()-this.getY()));
        acc = (G*p2.getM())/(this.Distance(p2)*this.Distance(p2));
        dT = 1/(100*acc);
        v = v + dT*acc;
        x =+ dT*v* Math.cos(ang);
        x =+ dT*v* Math.sin(ang);
    }
    public double Distance (Orbital p2){
        double r = Math.sqrt((p2.getX()-this.getX())*(p2.getX()-this.getX()) + (p2.getY()-this.getY())*(p2.getY()-this.getY()));
        return r;
    }
}
