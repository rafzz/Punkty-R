import java.awt.List;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class boot extends ApplicationFrame{
	
	final static DefaultCategoryDataset dataset = 
		      new DefaultCategoryDataset( );
	
	public boot( String applicationTitle , String chartTitle ) {
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


	//private static int tab[] =  {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};//new int[20];
	//private static int tab[] =  {14,15,16,17,18,5,6,7,8,9,10,11,19,2,0,1,3,4,12,13};
	private static int tab[] =  new int[70000]; //2000
	
	private static int R = 5;	
	
	private static int d=0;

	private static ArrayList<String> resultList = new ArrayList<>();
	public static void main(String[] args) {
		
		
		
		
		
		for(int b=0;b<20;b++) {
		
			Random rand = new Random();
			for(int o=0;o<70000;o++) {
				tab[o]=rand.nextInt(70000); //10000
			}
			
		
			d++;
		
			sort();
		
		
			System.out.println("\n\n---------------------KUBE£KI------------------------------");
		
			d++;
			bucket();
			
			System.out.println("\n\n===============================================");
		}
		
		boot chart = new boot("","");
		      chart.pack( );        
		      RefineryUtilities.centerFrameOnScreen( chart );        
		      chart.setVisible( true );

	}
	
	public static void bucket() {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		//start();
		for(int i : tab) {
			map.put((int)Math.floor(i/R), new ArrayList<>());
		}
		for(int i : tab) {
			map.get((int)Math.floor(i/R)).add(i);
		}
		start();
		//System.out.println(map);
		Instant start1 = Instant.now();
		int index=0;
		int licznik=0;
		
		double maxFromMap = Collections.max(map.keySet());
		while(index<=maxFromMap) {
			ArrayList<Integer> currentList=null;
			if(map.containsKey(index)) {
				currentList =map.get(index);
			}
			ArrayList<Integer> nextList = null;
			if(map.containsKey(index+1)) {
				nextList =map.get(index+1);
			}
			if(currentList!=null) {
				for(int i=0;i<currentList.size();i++) {
					//System.out.println();
					Integer currentPoint = currentList.get(i);
					for(int j=i+1;j<currentList.size();j++) {
						licznik++;
						//System.out.print("["+currentPoint+", "+currentList.get(j)+"]; ");
						resultList.add("["+currentPoint+", "+currentList.get(j)+"]; ");
					}
					if(nextList!=null) {	
						for(int p=0;p<nextList.size();p++) {

							Integer currentNextPoint = nextList.get(p);
							if(Math.abs(currentPoint-currentNextPoint)<R){
								licznik++;
								//System.out.print("["+currentPoint+", "+currentNextPoint+"]; ");
								resultList.add("["+currentPoint+", "+currentNextPoint+"]; ");
							}
						}	
					}	
				}
			}
			index++;
			//System.out.println();
		}
		stop();
		dataset.addValue( end() , "Bucket" , String.valueOf(d));
		System.out.println("Sort \n");
		System.out.println("\nLicznik: "+licznik);
		//}
	}
	
	public static void sort() {
		//Date startingTime = Calendar.getInstance().getTime();
		int licznik=0;
		//start();
		int z=0;
		Arrays.sort(tab);
		start();
		for(int i=0;i<tab.length;i++) {
			z=i+1;
			if(z>=tab.length) {
				break;
			}
			//System.out.println();
			while(Math.abs(tab[z]-tab[i])<R){
				licznik++;
				//System.out.print("["+tab[i]+", "+tab[z]+"]; ");
				resultList.add("["+tab[i]+", "+tab[z]+"]; ");
				z++;
				if(z>=tab.length) {
					break;
				}
			}
			//System.out.println();
		}	
		//Date now = Calendar.getInstance().getTime();
		stop();
		dataset.addValue( end() , "Sort" , String.valueOf(d) );
		System.out.println("Bucket \n"+end());
		System.out.println("Licznik: "+licznik);
		//long timeElapsed = now.getTime() - startingTime.getTime();
		//System.out.println("Time"+timeElapsed);
		
		
	}
	
	
	private static long start, stop;
	 
    public static void start() {
        start = System.currentTimeMillis(); // start timing
    }
 
    public static void stop() {
    	
        stop = System.currentTimeMillis(); // stop timing
    }
 
    public  static long Time() {
        return stop - start;
    }
 
    public static long end() {
        return Time(); // print execution time
    }
	
    
	
	

}
