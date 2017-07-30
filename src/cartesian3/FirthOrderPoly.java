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
public class FirthOrderPoly  extends Curve {

    public FirthOrderPoly(Color c, double first, double last, double xMult, double xShift, double yMult, double yShift) {
        super(c, first, last, xMult, xShift, yMult, yShift);
    }
    
    
    public void function()
    {
        for ( double x = firstValue; x < lastValue; x += step )
        {
            double y = - ( 3*Math.pow((x/100), 5) + 20*Math.pow((x/100), 4) -  10*Math.pow((x/100), 3) - 240*Math.pow((x/100), 2) - 250*(x/100) + 200 ) / 2;
            addPoint(x, y);
        //            System.out.println( x + ", " + y );
        }        
    }
}
