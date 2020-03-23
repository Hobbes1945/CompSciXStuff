package com.tychovonr.Riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;
import org.w3c.dom.css.Rect;

import java.awt.*;

public class RightHandRule extends AbstractRiemannExtended {
    public double slice​( com.tychovonr.Function.Function fun, double sleft, double sright,int mod,int[]coef) {

        return ((sright-sleft)*fun.evaluate(mod,sright,coef));
    }

    public void slicePlot​(PlotFrame pframe,  com.tychovonr.Function.Function fun, double sleft, double sright,int mod,int[]coef) {
        Trail quad = new Trail();
        quad.color = Color.red;
        pframe.addDrawable(quad);
        quad.addPoint(sleft,0);
        quad.addPoint(sright,0);
        quad.addPoint(sright,fun.evaluate(mod,sright,coef));
        quad.addPoint(sleft,fun.evaluate(mod,sright,coef));
        quad.addPoint(sleft,0);
    }

    public int add(int a, int b) {return a+b;}
}
