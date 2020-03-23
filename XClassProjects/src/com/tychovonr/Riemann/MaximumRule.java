package com.tychovonr.Riemann;

import com.tychovonr.Function.Function;
import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class MaximumRule extends AbstractRiemannExtended {
    public double slice​( com.tychovonr.Function.Function fun, double sleft, double sright,int mod,int[]coef) {
        if (fun.evaluate(mod,sleft,coef)>fun.evaluate(mod,sright,coef)){
            return ((sright-sleft)*fun.evaluate(mod,sleft,coef));
        } else {
            return ((sright-sleft)*fun.evaluate(mod,sright,coef));
        }
    }
    public void slicePlot​(PlotFrame pframe,  com.tychovonr.Function.Function fun, double sleft, double sright, int mod,int[] coef) {
        Trail quad = new Trail();
        quad.color = Color.red;
        pframe.addDrawable(quad);
        if (fun.evaluate(mod,sleft,coef)>fun.evaluate(mod,sright,coef)){
            quad.addPoint(sleft,0);
            quad.addPoint(sright,0);
            quad.addPoint(sright,fun.evaluate(mod,sleft,coef));
            quad.addPoint(sleft,fun.evaluate(mod,sleft,coef));
            quad.addPoint(sleft,0);
        } else {

            quad.addPoint(sleft,0);
            quad.addPoint(sright,0);
            quad.addPoint(sright,fun.evaluate(mod,sright,coef));
            quad.addPoint(sleft,fun.evaluate(mod,sright,coef));
            quad.addPoint(sleft,0);
        }
    }
}
