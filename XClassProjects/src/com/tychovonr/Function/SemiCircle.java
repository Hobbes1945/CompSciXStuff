package com.tychovonr.Function;

public class SemiCircle extends Function{
    @Override
    public double evaluate(int mod, double x,int[]coef) {
        return Math.sqrt(mod*mod-x*x);
    }

    @Override
    public double max(int mod, int domain, int[] coef, Function fun) {
        return mod;
    }

    @Override
    public double min(int mod, int domain, int[] coef, Function fun) {
        return 0;
    }

}
