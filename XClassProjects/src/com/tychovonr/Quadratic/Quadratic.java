package com.tychovonr.Quadratic;

public class Quadratic {
    double a = 1;
    double b = 2;
    double c = 1;
    //constructors
    public  Quadratic(){
         a = 1;
         b = 2;
         c = 1;
    }
    public Quadratic(double a,double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    //Getters and Setter
    public double getA() {
        return a;
    }
    public void setA(double a) {
        this.a = a;
    }
    public double getB() {
        return b;
    }
    public void setB(double b) {
        this.b = b;
    }
    public double getC() {
        return c;
    }
    public void setC(double c) {
        this.c = c;
    }
    //Actual Functions
    public boolean hasRealRoots(){
        double descr = b*b-(4*a*c);
        if(descr>=0){
            return true;
        }else {
            return false;
        }
    }
    public int numberOfRoots(){
        double descr = b*b-(4*a*c);
        if(descr<0){
            return 0;
        }
        else if (descr==0) {
            return 1;
        }else {
            return 2;
        }
    }
    public double[] getRootArray(){
        double[] roots = new double[2];
        roots[0] = (-b + Math.sqrt(b*b-(4*a*c)))/(2*a);
        roots[1] = (-b - Math.sqrt(b*b-(4*a*c)))/(2*a);
        return roots;
    }
    public double axisOfSymmetry(){
        return (-b/(2*a));
    }
    public double getExtremeValue(){
        return (a*(Math.pow(axisOfSymmetry(),2)+(b*axisOfSymmetry()))+c);
    }
    public boolean isMax(){
        if(a<0){
            return false;
        }else {
            return true;
        }
    }
    public double evaluateWith(double x){
        return (a*x*x)+(b*x)+c;
    }
}
