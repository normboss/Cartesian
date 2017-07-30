/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartesian3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Norm
 */
public class Cartesian3 
{
    
    Scanner scan;
    ArrayList<Curve> registeredCurves;
    ArrayList<Curve> selectedCurves;
    class PlaceHolder extends Curve 
    {
        public PlaceHolder(Color c, double first, double last, double xMult, double xShift, double yMult, double yShift) 
        {
            super(Color.BLACK, 0, 0, 0, 0, 0, 0);
        }
    
        public void function() {}
    }
    
    public Cartesian3()
    {
        scan = new Scanner(System.in);
        registeredCurves = new ArrayList<Curve>();
        selectedCurves = new ArrayList<Curve>();
    }
    
    void getSelectedCurves()
    {
        OnTheFly otf = new OnTheFly( Color.white, 0, 0, 0, 0, 0, 0 );
        otf.setName("PlaceHolder");
        registeredCurves.add( otf );
        
        Curve sinC = new SinC( Color.RED, -5, 5, 200, 500, 1, 500 );
        sinC.setName("sinC");
        registeredCurves.add( sinC);
//        sinC.function();

        Curve sinD =  new SinD( Color.GREEN, -5, 5, 200, 500, 1, 500 );
        sinD.setName("sinD");
        registeredCurves.add( sinD);
//        sinD.function();
        
        Curve sinE =  new SinE( Color.BLUE, -5, 5, 200, 500, 1, 500 );
        sinE.setName("sinE");
        registeredCurves.add( sinE);
//        sinE.function();
        
        Curve sinF =  new SinF( Color.YELLOW, -5, 5, 200, 500, 1, 500 );
        sinF.setName("sinF");
        registeredCurves.add( sinF);
//        sinF.function();
        
        Curve squareWave = new SquareWave( Color.WHITE, -50, 50, 200, 500, 1, 800 );
        squareWave.setName("squareWave");
        registeredCurves.add( squareWave );
//        squareWave.function();
        
        Curve sincFunc = new SincFunc( Color.MAGENTA, -15, 15, 10, 500, 200, 500 );
        sincFunc.setName("sincFunc");
        registeredCurves.add( sincFunc);
//        sincFunc.function();

        Curve parab1 = new Param1( Color.ORANGE, -500, 500, 10, 500, 1, 500 );
        parab1.setName("parab1");
        registeredCurves.add( parab1 );
//        parab1.function();
    
        Curve poly5th = new FirthOrderPoly( Color.RED, -500, 500, 1, 500, 1, 500 );
        poly5th.setName("poly5th");
        registeredCurves.add( poly5th );
//        poly5th.function();

//        showDialog();

        int curveNum = 0;
        System.out.println();
        for ( Curve c : registeredCurves )
        {
            System.out.println( curveNum + ": " + c.getName() );
            curveNum++;
        }
        
        System.out.println( "Enter the curve numbers:");
        System.out.println( "NUmber 0 selects an on the fly feature.");
        int num = 1;
        while ( (num = scan.nextInt()) >= 0 )
        {
            Curve c = null;
            if ( num == 0 ) {
                Calculator calc = new Calculator();
                calc.init();
                otf = calc.readEquation(false);
                otf.setCalculator(calc);
                c = otf;
                this.registeredCurves.add(0, otf);
            } else {
                c = this.registeredCurves.get(num);
            }
            this.selectedCurves.add(c);
            System.out.println( c.getName() );
        }
        System.out.println();
    }
    
    public void runGrapher()
    {
        Grapher g = new Grapher();
        getSelectedCurves();
        g.start( this.selectedCurves );
    }
    
    public void runCalculator()
    {
        OnTheFly otf = null;
        Calculator calc = new Calculator();
        calc.init();
        otf = calc.readEquation(true);
        otf.setCalculator(calc);
        double x = 0;
        boolean done = false;
        while ( !done )
        {
            System.out.println("Enter x:" );
            x = scan.nextDouble();
            double y = calc.compute(x);
            System.out.println("Y = " + y );
            System.out.print("Do another (y|n)?");
            if ( scan.nextLine() != "y" ) {
                done = true;
            }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        MP3PlayerClass player = new MP3PlayerClass();
//        player.Play( "D:\\Downloads\\32 Lux Aeterna.mp3" );
        player.Play( "D:\\Downloads\\01 Icarus.mp3" );
        Cartesian3 car = new Cartesian3();
        System.err.println("Enter 'g' for graphing or 'c' for calculator:");
        String s = car.scan.nextLine();
        if ( s.equalsIgnoreCase("g")) {
            car.runGrapher();
        }
        if ( s.equalsIgnoreCase("c")) {
            car.runCalculator();
        }
        
//        player.player.close();
    }
    
}
