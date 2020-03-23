package com.tychovonr.Riemann;

import com.tychovonr.Function.Function;
import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class LeftHandRule extends AbstractRiemannExtended{
    public double slice​( com.tychovonr.Function.Function fun, double sleft, double sright, int mod,int[]coef) {
        return ( (sright-sleft)*fun.evaluate(mod, sleft,coef));
    }

    public void slicePlot​(PlotFrame pframe,  com.tychovonr.Function.Function fun, double sleft, double sright, int mod,int[]coef) {
        Trail quad = new Trail();
        quad.color = Color.red;
        pframe.addDrawable(quad);
        quad.addPoint(sleft,0);
        quad.addPoint(sright,0);
        quad.addPoint(sright,fun.evaluate(mod,sleft,coef));
        quad.addPoint(sleft,fun.evaluate(mod,sleft,coef));
        quad.addPoint(sleft,0);
    }

}
