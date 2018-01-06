import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class boot {
	
	//private static int tab[] =  {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};//new int[20];
	//private static int tab[] =  {14,15,16,17,18,5,6,7,8,9,10,11,19,2,0,1,3,4,12,13};
	private static int tab[] =  new int[2000];
	
	private static int R = 5;	

	private static ArrayList<String> resultList = new ArrayList<>();
	public static void main(String[] args) {
		
		Random rand = new Random();
		for(int o=0;o<2000;o++) {
			tab[o]=rand.nextInt(10000);
		}
		
		for(int inn : tab) {
			System.out.print(inn+", ");
		}
		System.out.println();
		
		
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
		stop();
		System.out.println("\n"+end());
		System.out.println("Licznik: "+licznik);
		//wypisac wszystkie kombinacje z karzdego kube³ka i ich kombinacje z kolejnym kube³kiem czyli to co w while 
		
		System.out.println("\n\n---------------------KUBE£KI------------------------------");

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
		
		int index=0;
		licznik=0;
		
		double maxFromMap = Collections.max(map.keySet());
		while(index<=maxFromMap) {
			ArrayList<Integer> currentList =map.get(index);
			ArrayList<Integer> nextList =map.get(index+1);
			if(currentList!=null) {
				for(int i=0;i<currentList.size();i++) {
					//System.out.println();
					Integer currentPoint = currentList.get(i);
					for(int j=i+1;j<currentList.size();j++) {
						licznik++;
						//System.out.print("["+map.get(Double.valueOf(index)).get(i)+", "+map.get(Double.valueOf(index)).get(j)+"]; ");
						resultList.add("["+currentPoint+", "+currentList.get(j)+"]; ");
					}
					if(nextList!=null) {	
						for(int p=0;p<nextList.size();p++) {

							if(Math.abs(currentPoint-nextList.get(p))<R){
								licznik++;
								//System.out.print("["+map.get(Double.valueOf(index)).get(i)+", "+map.get(Double.valueOf(index+1)).get(p)+"]; ");
								resultList.add("["+currentPoint+", "+nextList.get(p)+"]; ");
							}
						}	
					}	
				}
			}
			index++;
			//System.out.println();
		}
		stop();
		System.out.println("\n"+end());
		System.out.println("\nLicznik: "+licznik);
		

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
 
    public static String end() {
        return "It took " + Time()+" seconds."; // print execution time
    }
	
	
	

}
