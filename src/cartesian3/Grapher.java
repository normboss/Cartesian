/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartesian3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 *
 * @author Norm
 */
public class Grapher extends JApplet 
{
    double cx,cy; 		/*Where do we want to center the brot?*/
    double scale; 		/*This is the "zoom" factor.*/
    int limit; 			/*Divergence check value.*/
    int lp; 		/*Convergence check value.*/
    double a1,b1,a2,b2; /*For calculating the iterations.*/
    int x,y; 			/*The pixel we are drawing.*/
    double ax,ay; 		/*The actual position of (x,y) in relation to 					  the Mandelbrot set.*/
    char key; 			/*Dummy value for keypresses.*/

    
    final static int maxCharHeight = 15;
    final static int minFontSize = 6;

    final static Color bg = Color.white;
    final static Color fg = Color.black;
    final static Color red = Color.red;
    final static Color white = Color.white;

    final static BasicStroke stroke = new BasicStroke(2.0f);
    final static BasicStroke wideStroke = new BasicStroke(8.0f);

    final static float dash1[] = {10.0f};
    final static BasicStroke dashed = new BasicStroke(1.0f, 
                                                      BasicStroke.CAP_BUTT, 
                                                      BasicStroke.JOIN_MITER, 
                                                      10.0f, dash1, 0.0f);
    Dimension totalSize;
    FontMetrics fontMetrics;
    ArrayList<Curve> selectedCurves;
    
    public void init() 
    {
        //Initialize drawing colors
        setBackground(bg);
        setForeground(fg);
        run();
    }

    public void run() 
    {
//        Curve sinC = new SinC( Color.RED, 200, 500, 1, 500 );
//        sinC.setName("sinC");
//        registeredCurves.add( sinC);
////        sinC.function();
//
//        Curve sinD =  new SinD( Color.GREEN, 200, 500, 1, 500 );
//        sinD.setName("sinD");
//        registeredCurves.add( sinD);
////        sinD.function();
//        
//        Curve sinE =  new SinE( Color.BLUE, 200, 500, 1, 500 );
//        sinE.setName("sinE");
//        registeredCurves.add( sinE);
////        sinE.function();
//        
//        Curve sinF =  new SinF( Color.YELLOW, 200, 500, 1, 500 );
//        sinF.setName("sinF");
//        registeredCurves.add( sinF);
////        sinF.function();
//        
//        Curve squareWave = new SquareWave( Color.WHITE, 200, 500, 1, 800 );
//        squareWave.setName("squareWave");
//        registeredCurves.add( squareWave );
////        squareWave.function();
//        
//        Curve sincFunc = new SincFunc( Color.MAGENTA, 10, 500, 200, 500 );
//        sincFunc.setName("sincFunc");
//        registeredCurves.add( sincFunc);
////        sincFunc.function();
//
//        Curve parab1 = new Param1( Color.ORANGE, 10, 500, 1, 500 );
//        parab1.setName("parab1");
//        registeredCurves.add( parab1 );
////        parab1.function();
//    
//        Curve poly5th = new FirthOrderPoly( Color.RED, 1, 500, 1, 500 );
//        poly5th.setName("poly5th");
//        registeredCurves.add( poly5th );
////        poly5th.function();
//
////        showDialog();

        int curveNum = 0;
        System.out.println();
        for ( Curve c : selectedCurves )
        {
            c.function();
        }
        
////        System.out.println( "Enter the curve numbers:");
////        int num = 0;
////        while ( sc.
    }
    
//    public void addCurve( Curve c )
//    {
//        this.selectedCurves.add(c);
//    }
    
    public void showDialog()
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PickFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PickFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PickFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PickFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                PickFunctions dialog = new PickFunctions(new javax.swing.JFrame(), true);

                
                
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    
    FontMetrics pickFont(Graphics2D g2, String longString, int xSpace) 
    {
        boolean fontFits = false;
        Font font = g2.getFont();
        FontMetrics fontMetrics = g2.getFontMetrics();
        int size = font.getSize();
        String name = font.getName();
        int style = font.getStyle();

        while ( !fontFits ) 
        {
            if ( (fontMetrics.getHeight() <= maxCharHeight)
                     && (fontMetrics.stringWidth(longString) <= xSpace) ) 
            {
                fontFits = true;
            }
            else if ( size <= minFontSize ) 
            {
                fontFits = true;
             }
            else 
            {
                g2.setFont(font = new Font( name, style, --size ));
                fontMetrics = g2.getFontMetrics();
            }
        }

        return fontMetrics;
    }
    
    public GeneralPath drawRegularPolygon( int xStart, int yStart, int sideLen, int numSides )
    {
        int xPts[] = new int[numSides+1];
        int yPts[] = new int[numSides+1];

        int L = sideLen;
        int N = numSides;
        double alpha = ((N-2)*180)/N;
        double beta = alpha / 2;
        double theta = 180 - alpha;
        double gamma = theta / 2;
        double angle = 0;
        double R = (L / 2) / Math.sin( Math.toRadians( gamma ));
        
        double x = 0;
        double y = 0;
         for ( int i = 0; i < N; i++ )
         {
             x = R * Math.cos( Math.toRadians( angle ));
             y = R * Math.sin( Math.toRadians( angle ));
             xPts[i] = (int) x + xStart;
             yPts[i] = (int) y + yStart;
             angle += theta;
         }
         xPts[N] = xPts[0];
         yPts[N] = yPts[0];

        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, numSides+1);
        
        polyline.moveTo( xPts[0], yPts[0] );
        for ( int index = 1; index < yPts.length; index++ ) 
        {
            polyline.lineTo( xPts[index], yPts[index] );
        }
        polyline.lineTo( xPts[0], yPts[0] );
        
        //g2.draw(polyline);
        return polyline;
    }
    
    void drawAxis(Graphics g)
    {
        g.setColor( Color.WHITE );
        g.drawLine( 0, 500, 1000, 500 );
        g.drawLine( 500, 0, 500, 1000 );
        
        for ( int x = 50; x < 1000; x += 50 )
        {
            g.drawLine( x, 505, x, 495 );
        }
        for ( int y = 50; y < 1000; y += 50 )
        {
            g.drawLine( 495, y, 505, y );
        }
    }
 
    public void paint(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension d = getSize();
        int gridWidth = d.width / 6;
        int gridHeight = d.height / 2;

        g2.setBackground(Color.white);
        Color c = g2.getBackground();
        this.setBackground(Color.black);
        fontMetrics = pickFont(g2, "Filled and Stroked GeneralPath", gridWidth);
        
//        Color fg3D = Color.lightGray;
      

        g2.setColor( Color.WHITE );
        drawAxis( g2 );
        int xPrev = 0;
        int yPrev = 0;
        int loopcnt = 0;
        for ( Curve curve : selectedCurves )
        {
            g2.setColor( curve.getColor() );
            Iterator<Point> I = curve.getIterator();
            while ( I.hasNext() )
            {
                Point p = I.next();
                int xInt = curve.xAdjust( p.getx() );
                int yInt = curve.yAdjust( p.gety() );
                if ( loopcnt == 0 ) 
                {
                    xPrev = xInt;
                    yPrev = yInt;
                }
                else if ( xInt != xPrev  || yInt != yPrev )
                {
                    g2.drawLine( xPrev, yPrev, xInt, yInt );
                    xPrev = xInt;
                    yPrev = yInt;
                }
                loopcnt++;
            }
        }

        this.repaint();           
    }
   
          
    public int calcArea( int h, int w )
    {
        return h*w;
    }

    public void start( ArrayList<Curve> curves )
    {
        JFrame f = new JFrame("Grapher");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        Grapher applet = new Grapher();
        applet.selectedCurves = curves;
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setSize(new Dimension(1000,2000));
        f.setVisible(true);
    }

}