/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartesian3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Norm
 */
public abstract class Curve 
{
    private ArrayList<Point> pointList;
    private Color color;
    double xMult, yMult, xShift, yShift, firstValue, lastValue;
    String name;
    double step;
    
    public Curve( Color c, double firstValue, double lastValue, double xMult, double xShift, double yMult, double yShift )
    {
        this.color = c;
        this.xMult = xMult;
        this.xShift = xShift;
        this.yMult = yMult;
        this.yShift = yShift;
        this.firstValue = firstValue;
        this.lastValue = lastValue;
        pointList = new ArrayList<Point>();
        step = (lastValue - firstValue) / 1000;
    }

    void setName( String name )
    {
        this.name = name;
    }
    
    String getName()
    {
        return name;
    }
    
    void addPoint( double x, double y )
    {
        Point p = new Point( x, y );
        pointList.add(p);
    }

    public Iterator getIterator()
    {
        return pointList.iterator();
    }

    public Color getColor() 
    {
        return this.color;
    }

    public int xAdjust( double x )
    {
         return (int) (xMult*x + xShift);
    }

    public int yAdjust( double y )
    {
         return (int)(yMult*y + yShift);
    }

    abstract public void function();
}
