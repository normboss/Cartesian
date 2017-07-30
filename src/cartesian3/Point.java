/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartesian3;

/**
 *
 * @author Norm
 */
public class Point 
{
    private double x;
    private double y;
    
    Point( double x, double y )
    {
        this.x = x;
        this.y = y;
    }
    
    double getx() { return x; }
    double gety() { return y; }
}
