/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartesian3;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 *
 * @author Norm
 */
public class DoubleStack 
{
    private String str;
    private Stack innerStack;
    private static int itemInAllStacks = 0;
    
    DoubleStack()
    {
        str = "Hello";
        innerStack = new Stack();
    }
    
    public void push( double d) 
    {
        if ( innerStack == null ) {
            innerStack = new Stack();
        }
        Double D = new Double(d);
        innerStack.push(D);
        DoubleStack.itemInAllStacks++;
    }
    
    public double pop()
    {
        Double D;
        if ( !innerStack.isEmpty()) 
        {
            D = (Double) innerStack.pop();
            DoubleStack.itemInAllStacks--;
        } else {
            System.out.println("Throwing empty stack exception in MyStack:pop().");
            throw new EmptyStackException();
        }
        return D.doubleValue();
    }
    
    public static int getCount()
    {
        return DoubleStack.itemInAllStacks;
    }
    
}