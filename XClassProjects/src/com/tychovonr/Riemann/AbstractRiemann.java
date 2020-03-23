package com.tychovonr.Riemann;
import org.opensourcephysics.display.Trail;

import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

/**
 * @author tychovonr
 */
public abstract class AbstractRiemann {
    public AbstractRiemann(){
    }
    double calculateDeltaX​(double left, double right, int subintervals){
        return (right-left)/subintervals;
    }
    public abstract double slice​(org.dalton.polyfun.Polynomial poly, double sleft, double sright);
    public abstract void slicePlot​(org.opensourcephysics.frames.PlotFrame pframe, org.dalton.polyfun.Polynomial poly, double sleft, double sright);
    public double rs​(org.dalton.polyfun.Polynomial poly, double left, double right, int subintervals){
         double rs = 0;
         double sLeft = 0;
         double sRight = 0;
         for (int i = 0; i < subintervals ; i++) {
             sLeft = calculateDeltaX​(left, right, subintervals)*i;
             sRight = calculateDeltaX​(left, right, subintervals)*(i+1);
             rs = rs+ slice​(poly,sLeft,sRight);
    }
         return rs;
     }
     void rsAcc​(org.opensourcephysics.frames.PlotFrame pframe, org.dalton.polyfun.Polynomial poly, double precision, double base){
         Trail acc = new Trail();
         acc.color = Color.BLACK;
         pframe.addDrawable(acc);
         for (int i = -10; i <10  ; i++) {
             double yCord =  rs​(poly,base,i, 100);
             acc.addPoint(i, yCord);

         }
     }
     void rsPlot​(org.opensourcephysics.frames.PlotFrame pframe, org.dalton.polyfun.Polynomial poly, double left, double right, int subintervals){
         Trail function = new Trail();
         function.color = Color.BLACK;
         pframe.addDrawable(function);
         double dif = calculateDeltaX​(left,right,subintervals);
         for (double i = 0; i <subintervals*100 ; i++) {
             function.addPoint(left+((i*dif)/100),poly.evaluateWith(left+((i*dif)/100)));
         }
         for (int i = -50; i < subintervals; i++) {
             slicePlot​(pframe,poly,dif*i,dif*(i+1));
         }
     }
}