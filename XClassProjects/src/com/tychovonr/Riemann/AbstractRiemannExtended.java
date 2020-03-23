package com.tychovonr.Riemann;

import org.opensourcephysics.display.Trail;

import java.awt.*;

public abstract class AbstractRiemannExtended {
    /**
     * Constructor
     */
    public AbstractRiemannExtended(){
    }

    /**
     *
     * @param left The left side of the interval
     * @param right the right side
     * @param subintervals how many divisions will be made
     * @return the width of the rectangle
     */
    double calculateDeltaX​(double left, double right, int subintervals){
        return (right-left)/subintervals;
    }

    /**
     *
     * @param fun The function
     * @param sleft left side of the slice
     * @param sright right side of the slice
     * @param mod changes the function type
     * @param coef deals with coeffecients for the polynomial
     * @return area of the slice
     */
    public abstract double slice​(com.tychovonr.Function.Function fun, double sleft, double sright,int mod,int[]coef);

    /**
     *
     * @param pframe the plot that is graphed on
     * @param fun the function
     * @param sleft left side of the slice
     * @param sright right side of the slice
     * @param mod chances the function type
     * @param coef deals with coefficients
     */
    public abstract void slicePlot​(org.opensourcephysics.frames.PlotFrame pframe, com.tychovonr.Function.Function fun, double sleft, double sright,int mod,int[]coef);

    /**
     *
     * @param fun the function
     * @param left the base of the sum
     * @param right the x values of the point it goes to
     * @param subintervals how many rectangles
     * @param mod for changes
     * @param coef for coefficients
     * @return
     */
    public double rs​( com.tychovonr.Function.Function fun, double left, double right, int subintervals,int mod,int[]coef){
        double rs = 0;
        double sLeft = 0;
        double sRight = 0;
        for (int i = 0; i < subintervals ; i++) {
            sLeft = calculateDeltaX​(left, right, subintervals)*i;
            sRight = calculateDeltaX​(left, right, subintervals)*(i+1);
            rs = rs+ slice​(fun,sLeft,sRight,mod,coef);
        }
        return rs;
    }

    /**
     *
     * @param pframe the plot
     * @param fun the function
     * @param domain how far to the left and right of the function it looks
     * @param base starting x cord
     * @param mod changes within functions
     * @param coef coefficients in the polynomial
     */
    void rsAcc​(org.opensourcephysics.frames.PlotFrame pframe, com.tychovonr.Function.Function fun, int domain, double base,int mod,int[] coef){
        Trail acc = new Trail();
        acc.color = Color.BLACK;
        pframe.addDrawable(acc);
        for (int i = -domain; i <domain  ; i++) {
            double yCord =  rs​(fun,base, i,100,mod,coef);
            acc.addPoint(i, yCord);

        }
    }

    /**
     *
     * @param pframe the plot
     * @param fun the function
     * @param left left side of the frame
     * @param right right side of the frame
     * @param subintervals number of rectangles
     * @param mod changes
     * @param coef coefficients of the polynomial
     */
    void rsPlot​(org.opensourcephysics.frames.PlotFrame pframe, com.tychovonr.Function.Function fun, double left, double right, int subintervals, int mod,int[] coef){
        Trail function = new Trail();
        function.color = Color.BLACK;
        pframe.addDrawable(function);
        double dif = calculateDeltaX​(left,right,subintervals);
        for (double i = 0; i <subintervals*200 ; i++) {
            function.addPoint(left+((i*dif)/200),fun.evaluate(mod, left+((i*dif)/200),coef));
        }
        for (int i = -50; i < subintervals; i++) {
            slicePlot​(pframe,fun,dif*i,dif*(i+1),mod,coef);
        }
    }

}
