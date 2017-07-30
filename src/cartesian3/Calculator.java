/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartesian3;

import java.awt.Color;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author Norm
 */
public class Calculator
{
    String input;
    Scanner sc;
    Stack stack = new Stack();
    Instruction[] program = new Instruction[100];
    int ip = 0;

    enum INST_TYPE {
        OPERAND,
        NUMBER,
        PLUS,
        MINUS,
        MULT,
        DIV,
        POW,
        EXP,
        LOG10,
        LN,
        SIN,
        ASIN,
        COS,
        ACOS,
        TAN,
        ATAN,
        SQRT,
        CHS,
        RECIP,
        PI,
        RAD,
        DEG,
        ECONST
    }
    
    class CalcType 
    {
       CalcType( INST_TYPE t, String s )     
       {
           type = t;
           text = s;
       }
        INST_TYPE type;
        String text;
    }
//    CalcType[] calcTypes = 
//    { 
    
    HashMap<String, INST_TYPE> map = new HashMap<String, INST_TYPE>();
    
    class Instruction {
        INST_TYPE type;
        double value;
    }
    
    public void init()
    {
        sc = new Scanner(System.in);
//        map.put(key, input)
        map.put("x", INST_TYPE.OPERAND );
        map.put("?", INST_TYPE.NUMBER );
        map.put("+", INST_TYPE.PLUS );
        map.put("-", INST_TYPE.MINUS  );
        map.put("*", INST_TYPE.MULT );
        map.put("/", INST_TYPE.DIV );
        map.put("^", INST_TYPE.POW  );
        map.put("exp", INST_TYPE.EXP );
        map.put("log10", INST_TYPE.LOG10 );
        map.put("ln", INST_TYPE.LN );
        map.put("sin", INST_TYPE.SIN );
        map.put("asin", INST_TYPE.ASIN );
        map.put("cos", INST_TYPE.COS );
        map.put("acos", INST_TYPE.ACOS );
        map.put("tan", INST_TYPE.TAN );
        map.put("atan", INST_TYPE.ATAN );
        map.put("sqrt", INST_TYPE.SQRT );
        map.put("chs", INST_TYPE.CHS );
        map.put("inv", INST_TYPE.RECIP );
        map.put("pi", INST_TYPE.PI );
        map.put("rad", INST_TYPE.RAD );
        map.put("deg", INST_TYPE.DEG );
        map.put("e", INST_TYPE.ECONST );
    }
    
    
    public OnTheFly readEquation( boolean calculatorMode )
    {
        Set<String> set = map.keySet();
        
        System.out.println("Enter an expression:");
        input = sc.nextLine();
        double value = 0;
        
        for (StringTokenizer st = new StringTokenizer(input); st.hasMoreTokens();) 
        {
            String token = st.nextToken();
            System.out.println( token );
            INST_TYPE type = (INST_TYPE)map.get( token );
            Instruction inst = new Instruction();
            if ( type == null ) {
                inst.value = Double.parseDouble(token);
                inst.type = INST_TYPE.NUMBER;
            } else {
                inst.type = type;
            }
            this.program[ip++] = inst;
        }
        
//        System.out.println("Enter first x value:");
//        double firstX = sc.nextDouble();
//        System.out.println("Enter last x value:");
//        double lastX = sc.nextDouble();
//        System.out.println("Enter step size:");
//        double stepSize = sc.nextDouble();

        Color color = Color.WHITE;
        double firstValue = 0;
        double lastValue = 0;
        double xMult = 0;
        double xShift = 0;
        double yMult = 0;
        double yShift = 0;
        String name = "NONAME";
        
        if ( !calculatorMode )
        {
            boolean done = false;
            while ( !done )
            {
                System.out.println( "firstValue, lastValue, xMult, xShift, yMult, yShift" );
                int i;
                for ( i=0; i < 6; i++ )
                {
                    double d = sc.nextDouble();
                    if ( d == -1 ) {
                        break;
                    }
                    switch (i)
                    {
                        case 0:
                            firstValue = d;
                            break;
                        case 1:
                            lastValue = d;
                            break;
                        case 2:
                            xMult = d;
                            break;
                        case 3:
                            xShift = d;
                            break;
                        case 4:
                            yMult = d;
                            break;
                        case 5:
                            yShift = d;
                            break;
                    }
                }
                if ( i < 6) {
                    System.out.println("Too few parameters!  Try again.");
                } else {
                    done = true;
                }
            }
            System.out.println("Enter curve name:");
            name = sc.nextLine();
        }
        OnTheFly otf = new OnTheFly( Color.WHITE, firstValue, lastValue, xMult, xShift, yMult, yShift );
        otf.setName(name);
        
        return otf;
    }
    
    public double compute( double x )
    {
        double y = 0;
        double x0, x1;
        DoubleStack st = new DoubleStack();
        
        for ( int i=0; i < ip; i++ )
        {
            Instruction inst = program[i];
            switch ( inst.type )
            {
                case OPERAND:
                    st.push(x);
                    break;
                case NUMBER:
                    st.push(inst.value);
                    break;
                case PLUS:
                    st.push( st.pop() + st.pop() );
                    break;
                case MINUS:
                    x0 = st.pop();
                    x1 = st.pop();
                    st.push( x1 - x0 );
                    break;
                case MULT:
                    st.push( st.pop() * st.pop() );
                    break;
                case DIV:
                    x0 = st.pop();
                    x1 = st.pop();
                    if ( x0 > 0 ) {
                        st.push( x1 / x0 );
                    } else {
                        st.push( 10000 );
                    }
                    break;
                case POW:
                    x0 = st.pop();
                    x1 = st.pop();
                    st.push( Math.pow( x1, x0 ));
                    break;
                case EXP:
                    st.push( Math.exp( st.pop() ));
                    break;
                case LOG10:
                    st.push( Math.log10(st.pop() ));
                    break;
                case LN:
                    st.push( Math.log(st.pop() ));
                    break;
                case SIN:
                    st.push( Math.sin(st.pop() ));
                    break;
                case ASIN:
                    st.push( Math.asin(st.pop() ));
                    break;
                case COS:
                    st.push( Math.cos(st.pop() ));
                    break;
                case ACOS:
                    st.push( Math.acos(st.pop() ));
                    break;
                case TAN:
                    st.push( Math.tan( st.pop() ));
                    break;
                case ATAN:
                    st.push( Math.atan( st.pop() ));
                    break;
                case SQRT:
                    st.push( Math.sqrt( st.pop() ));
                    break;
                case CHS:
                    st.push( -st.pop() );
                    break;
                case RECIP:
                    st.push( 1.0 / st.pop() );
                    break;
                case PI:
                    st.push( Math.PI );
                    break;
                case RAD:
                    st.push( Math.toRadians( st.pop() ));
                    break;
                case DEG:
                    st.push( Math.toDegrees( st.pop() ));
                    break;
                case ECONST:
                    st.push( Math.E );
                default:
            }
        }
        
        y = st.pop();
        return y;
    }
    
//    public void run()
//    {
//        init();
//        do
//        {
//            readEquation( );
//            System.out.println("\nComputing Results");
//            for ( double x = -5; x <= 5; x += 1)
//            {
//                double y = compute(x);
//                System.out.println("y = F( " + x + " ) = " + y);
//            }
//            System.out.println("Continue?");
//        } while ( sc.nextBoolean() );
//    }
}