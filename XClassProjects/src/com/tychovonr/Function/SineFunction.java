package com.tychovonr.Function;

public class SineFunction extends Function{
    public double evaluate(int mod, double x,int[]coef) {
        if (mod == 0){
            return Math.sin(x);
        }
        else if(mod ==1){
            return Math.cos(x);
        }
        else if(mod==2){
            return Math.cos(x);
        }
        else {
            return x;
        }
    }

    @Override
    public double max(int mod, int domain, int[] coef, Function fun) {
        return 1;
    }

    @Override
    public double min(int mod, int domain, int[] coef, Function fun) {
        return -1;
    }

}
