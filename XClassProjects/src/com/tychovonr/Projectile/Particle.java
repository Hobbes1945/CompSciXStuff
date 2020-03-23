package com.tychovonr.Projectile;

public class Particle {
    double x;
    double y;
    double v;
    double velX;
    double velY;
    double oldVelY;
    double oldVelX;
    double accX;
    double accY;
    double g;
    double theta;
    double beta;
    double mass;
    double dT;
    double t;
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getAccX() {
        return accX;
    }

    public void setAccX(double accX) {
        this.accX = accX;
    }

    public double getAccY() {
        return accY;
    }

    public void setAccY(double accY) {
        this.accY = accY;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }
    public Particle(double x, double y, double v,  double accX, double accY, double g, double theta, double beta, double mass, double t) {
        this.x = x;
        this.y = y;
        this.v = v;
        theta = Math.toRadians(theta);
        this.theta = theta;
        this.velX = v*Math.cos(theta);
        this.velY = v*Math.sin(theta);
        this.accX = accX;
        this.accY = accY;
        this.g = g;
        this.beta = beta;
        this.mass = mass;
        this.dT = t;
    }
    public Particle(double x, double y,double v, double velX,double velY, double accX, double accY, double g, double beta, double mass, double t, double dT) {
        this.x = x;
        this.y = y;
        this.velX =velX;
        this.velY = velY;
        this.accX = accX;
        this.accY = accY;
        this.g = g;
        this.beta = beta;
        this.mass = mass;
        this.t = t;
        this.dT = dT;
    }
    public Particle(double v, double g, double beta, double mass, double theta, double dT) {
        this.x = 0;
        this.y = 0;
        this.accX = 0;
        this.accY = 0;
        theta = Math.toRadians(theta);
        this.theta = theta;
        this.velX = v*Math.cos(theta);
        this.velY = v*Math.sin(theta);
        this.g = g;
        this.beta = beta;
        this.mass = mass;
        this.t = 0;
        this.dT = dT;
    }
    public Particle(){
        this.x = 0;
        this.y = 100;
        this.v = 10;
        this.theta = 270;
        theta = theta*(180/3.14159265);
        this.velX = 5;
        this.velY = 1000;
        this.accX = 0;
        this.accY = 0;
        this.g = -9.81;
        this.beta = 0;
        this.mass = 10;
        this.t = .1;
        this.dT = .01;
    }
    public void rectStep(){
        dT = Math.abs(dT);
        accY = ((mass*(g)-beta*velY*Math.abs(velY))/mass);
        velY = velY+ dT*accY;
        y = y+ dT*velY;
        accX = ((-beta*velX*Math.abs(velX))/mass);
        velX = velX+ dT*accX;
        x = x+ dT*velX;
        t = t + Math.abs(dT);
    }
    public void revRectStep(){
        dT = -Math.abs(dT);
        accY = ((mass*(g)-beta*velY*Math.abs(velY))/mass);
        velY = velY+ dT*accY;
        y = y+ velY*dT;
        accX = ((-beta*velX*Math.abs(velX))/mass);
        velX = velX+ dT*accX;
        x = x+ velX*dT;
        t = t + Math.abs(dT);
    }
    public void squareStep(){
        dT = Math.abs(dT);
        dT = 1/(10*v*v);
        //calculating simpsons rule for Y
    /*    velY = velY + (dT/6)*(accY + 4*(g) + ((mass*(g)-(beta*(v*velY)))/mass));
        accY = (velY-oldVelY)/dT;
        oldVelY = velY;
        y = y + velY*(dT);

        //calculating simspons rule for X
        velX = velX + (dT/6)*(accX + 4*(0) + ((mass*(0)-(beta*(v*velX)))/mass));
        accX = (velX-oldVelX)/dT;
        oldVelX = velX;
        x = x +velX*dT;

     */
        accY = g -beta*v*velY/mass;
        velY += accY * dT;
        y = y + velY*(dT);

        accX = -beta*v*velX/mass;
        velX += accX * dT;
        x = x + velX*(dT);

        v = Math.sqrt(velX*velX + velY*velY);
        t = t + Math.abs(dT);

    }
    public void reverseStep(){
        dT = -Math.abs(dT);
        velY =velY + (dT/6)*(accY + 4*(g) + ((mass*(g)-(beta*(v*Math.abs(velY))))/mass));
        accY = (velY-oldVelY)/dT;
        oldVelY = velY;
        y = y + velY*(dT);


        velX = velX + (dT/6)*(accX + 4*(0) + ((mass*(0)-(beta*(v*Math.abs(velX))))/mass));
        accX = (velX-oldVelX)/dT;
        oldVelX = velX;
        x = x +velX*dT;

        v = Math.sqrt((velY*velY)+(velX*velX));
        t = t + Math.abs(dT);
    }

}
