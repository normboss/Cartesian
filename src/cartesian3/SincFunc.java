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
public class SincFunc extends Curve {

    public SincFunc(Color c, double first, double last, double xMult, double xShift, double yMult, double yShift) {
        super(c, first, last, xMult, xShift, yMult, yShift);
    }
    
    public void function()
    {
        for ( double x = firstValue; x < lastValue; x += step )
        {
            double y = -Math.sin(x) / x;
            addPoint(x, y);
//            System.out.println( x + ", " + y );
       }
    }
}
