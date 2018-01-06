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
	private static int tab[] =  new int[1000];
	
	private static int R = 6;	

	private static ArrayList<String> resultList = new ArrayList<>();
	public static void main(String[] args) {
		
		Random rand = new Random();
		for(int o=0;o<1000;o++) {
			tab[o]=rand.nextInt(1000);
		}
		
		for(int inn : tab) {
			System.out.print(inn+", ");
		}
		System.out.println();
		
		
		int licznik=0;
		start();
		int z=0;
		Arrays.sort(tab);
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

		HashMap<Double, ArrayList<Integer>> map = new HashMap<Double, ArrayList<Integer>>();
		start();
		for(int i : tab) {
			map.put(Math.floor(i/R), new ArrayList<>());
		}
		for(int i : tab) {
			map.get(Math.floor(i/R)).add(i);
		}
		
		//System.out.println(map);
		
		int index=0;
		licznik=0;
		while(index<=Collections.max(map.keySet())) {
			if(map.get(Double.valueOf(index))!=null) {
				for(int i=0;i<map.get(Double.valueOf(index)).size();i++) {
					//System.out.println();
					for(int j=i+1;j<map.get(Double.valueOf(index)).size();j++) {
						licznik++;
						//System.out.print("["+map.get(Double.valueOf(index)).get(i)+", "+map.get(Double.valueOf(index)).get(j)+"]; ");
						resultList.add("["+map.get(Double.valueOf(index)).get(i)+", "+map.get(Double.valueOf(index)).get(j)+"]; ");
					}
					if(map.get(Double.valueOf(index+1))!=null) {	
						for(int p=0;p<map.get(Double.valueOf(index+1)).size();p++) {

							if(Math.abs(map.get(Double.valueOf(index)).get(i)-map.get(Double.valueOf(index+1)).get(p))<R){
								licznik++;
								//System.out.print("["+map.get(Double.valueOf(index)).get(i)+", "+map.get(Double.valueOf(index+1)).get(p)+"]; ");
								resultList.add("["+map.get(Double.valueOf(index)).get(i)+", "+map.get(Double.valueOf(index+1)).get(p)+"]; ");
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
