package com.tychovonr.Function;

public class LogFunction extends Function {

    @Override
    public double evaluate(int mod, double x,int[] coef) {
        return Math.log(x);
    }

    @Override
    public double max(int mod, int domain,int[]ceof, Function fun) {
        return Math.log(domain/2);
    }

    @Override
    public double min(int mod, int domain,int[]coef, Function fun) {
        return -2;
    }
}
