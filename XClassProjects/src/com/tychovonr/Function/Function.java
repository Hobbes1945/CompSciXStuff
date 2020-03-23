package com.tychovonr.Function;
import org.opensourcephysics.display.Trail;

import java.awt.*;
import java.util.Stack;
import java.util.LinkedList;
import java.util.EmptyStackException;
public abstract class Function {
    public Function(){
    }
    public abstract double evaluate(int mod, double x,int[] coef);
    public abstract double max(int mod, int domain, int[]coef, Function fun);
    public abstract double min(int mod, int domain,int[]coef, Function fun);
}
