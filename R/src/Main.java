import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Main extends ApplicationFrame{

    private static ArrayList<Point> points = new ArrayList<>();
    private static ArrayList<double []> timesJarvisGraham = new ArrayList<>();
    public static void main(String[] args) {
//        points.add(new Point(8, 1));
//        points.add(new Point(-5, -1));
//        points.add(new Point(0, 8));
//        points.add(new Point(2, 8));
//        points.add(new Point(4, -4));
//        points.add(new Point(-3, 9));
//        points.add(new Point(3, 8));
//        points.add(new Point(4, 8));
//        points.add(new Point(1, -4));
//        points.add(new Point(-9, 0));
//        points.add(new Point(2, 4));
//        points.add(new Point(8, -1));
//        points.add(new Point(-3, 2));
//        points.add(new Point(4, 1));
//        points.add(new Point(-4, -4));
//        points.add(new Point(-4, -2));
//        points.add(new Point(-4, -3));
//        points.add(new Point(-4, -1));
//        points.add(new Point(-12, 3));
//        points.add(new Point(-4, 0));
//        points.add(new Point(2, -4));
//        points.add(new Point(3, 7));
        double startTime;
        double stopTime;
        double elapsedTime;
        for(int i = 0; i < 100; ++i){
            points = numbersLottery(20, 20, 10);
//            for (Point point: points) {
//                System.out.println(point);
//            }
            System.out.println(i);
            System.out.println("----------------------------------------------------------------------------------------");

            timesJarvisGraham.add(new double[2]);

            startTime = System.nanoTime();

            Jarvis jarvis = new Jarvis();
            jarvis.runJarvis(points).size();

            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;

            timesJarvisGraham.get(i)[0] = elapsedTime/1000000;
            //System.out.println("Time: " + (elapsedTime/1000000));
            System.out.println("--------------------------------------------------------------");

            startTime = System.nanoTime();

            Graham graham = new Graham();
            graham.runGraham(points).size();
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;

            timesJarvisGraham.get(i)[1] = elapsedTime/1000000;

            System.out.println("--------------------------------------------------------------");

        }
        int d=0;
        for (double[] time: timesJarvisGraham) {
        	
            System.out.println("Time Jarvis: " + time[0] + " Time Graham: " + time[1]);
            d++;
            dataset.addValue( time[0] , "Jarvis" , String.valueOf(d));
            d++;
            dataset.addValue( time[1], "Graham" , String.valueOf(d));
        }
        
        Main chart = new Main("", 
		         "");
		      chart.pack( );        
		      RefineryUtilities.centerFrameOnScreen( chart );        
		      chart.setVisible( true );
    }

    private static ArrayList<Point> numbersLottery(int pointsSize, int firstParameter, int secondParamater){
        ArrayList<Point> lotteryPoints = new ArrayList<>();
        Random rn = new Random();
        int same = 0;
        while(lotteryPoints.size() < pointsSize){
            int x = rn.nextInt(firstParameter) - secondParamater;
            int y = rn.nextInt(firstParameter) - secondParamater;
            boolean samePoints = false;
            for (Point point: lotteryPoints) {
                if(point.x == x && point.y == y){
                    samePoints = true;
                    System.out.println("Same: " + (++same));
                    break;
                }
            }
            if(!samePoints){
                lotteryPoints.add(new Point(x,y));
            }
        }
        return lotteryPoints;
    }
    
    final static DefaultCategoryDataset dataset = 
		      new DefaultCategoryDataset( );
    
    public Main( String applicationTitle , String chartTitle ) {
		super( applicationTitle );        
	      JFreeChart barChart = ChartFactory.createBarChart(
	         chartTitle,           
	         "",            
	         "Time",            
	         dataset,          
	         PlotOrientation.VERTICAL,           
	         true, true, false);
	         
	      ChartPanel chartPanel = new ChartPanel( barChart );        
	      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
	      setContentPane( chartPanel );
	}
}