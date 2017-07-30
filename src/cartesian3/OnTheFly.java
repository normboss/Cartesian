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
public class OnTheFly extends Curve 
{
    Calculator calc;
    
    public OnTheFly(Color c, double firstValue, double lastValue, double xMult, double xShift, double yMult, double yShift) 
    {
        super(c, firstValue, lastValue, xMult, xShift, yMult, yShift);
    }
    
    void setCalculator( Calculator c )
    {
        calc = c;
    }
    
    public void function()
    {
        for ( double x = firstValue; x < lastValue; x += step )
        {
            double y = calc.compute(x);
            addPoint(x, -y);
//            System.out.println( x + ", " + y );
       }
    }
    
}
