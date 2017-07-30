/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartesian3;

import java.awt.Color;

/**
 *
 * @author Norm
 */
public class SinF extends Curve
{
    public SinF(Color c, double first, double last, double xMult, double xShift, double yMult, double yShift) 
    {
        super(c, first, last, xMult, xShift, yMult, yShift);
    }
    
    public void function()
    {
        final double F_const = 7;
        final double multipler = 100;
        
        for ( double x = firstValue; x < lastValue; x += step )
        {
            double A  = x;
            double B = A * Math.PI;
            double F = multipler * Math.sin(B * F_const);
            double y = F;
            addPoint(x, y);
//            System.out.println( x + ", " + y );
        }
    }
    
}
