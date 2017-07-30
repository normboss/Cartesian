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
public class SquareWave extends Curve
{

    public SquareWave(Color c, double first, double last, double xMult, double xShift, double yMult, double yShift) 
    {
        super(c, first, last, xMult, xShift, yMult, yShift);
    }
    
    public void function()
    {
        final double C_const = 1;
        final double D_const = 3;
        final double E_const = 5;
        final double F_const = 7;
        final double multipler = 100;
        
       for ( double x = firstValue; x < lastValue; x += step )
       {
            double A  = x;
            double B = A * Math.PI;
            double C = multipler * Math.sin(B * C_const);
            double D = multipler * Math.sin(B * D_const);
            double E = multipler* Math.sin(B * E_const);
            double F = multipler * Math.sin(B * F_const);
            double G = (C/C_const) + (D/D_const) + (E/E_const) + (F/F_const);
            double y = G;
            addPoint(x, y);
//            System.out.println( x + ", " + y );
       }
    }

}
