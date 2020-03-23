package com.tychovonr.Function;

public class PolyFunction extends Function{
    public double evaluate(int mod, double x,int[] coef) {
        int value = 0;
        for (int i = 0; i <coef.length ; i++) {
            value+= coef[i]*Math.pow(x,i);
        }
        return value;
    }
    public double max(int mod, int domain,int[]coef, Function fun) {
        return fun.evaluate(0,domain/2,coef);
    }
    public double min(int mod, int domain,int[]coef, Function fun) {
        if((coef.length)%2==0){
            return fun.evaluate(0,-domain/2,coef);
        }
        else {
            return 0;
        }
    }
}
