import java.awt.*;
import java.util.ArrayList;

public class Jarvis {

    private ArrayList<Point> points = new ArrayList<>();

    public Jarvis() {
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<Point> runJarvis(ArrayList<Point> points_){
        points.clear();
        points = new ArrayList<>(points_);

        int firstApexIndex = findFirstApexIndex();
        Point p1 = points.get(firstApexIndex);//staje sie nowe
        Point p0 = new Point(p1.x-10,p1.y);//staje sie p1
        ArrayList<Point> shellPoints = new ArrayList<>();
        shellPoints.add(p1);
        while(true){
            int [] shellVector = getVector(p1,p0);
            double biggestDegree = -1000;
            int biggestI = 0;
            for(int i = 0; i < points.size();++i){
                int tempVector[] = getVector(p1,points.get(i));
                //System.out.println(points.get(i)+ " " + tempVector[0] + " " + tempVector[1] + " " + calculateVectorLength(tempVector) + " " + Math.acos(calculateVectorsDegrees(shellVector,tempVector))*180/Math.PI);
                //System.out.println(Math.acos(calculateVectorsDegrees(shellVector,tempVector))*180/Math.PI);
                if(Math.acos(calculateVectorsDegrees(shellVector,tempVector))*180/Math.PI > biggestDegree){
                    biggestDegree = Math.acos(calculateVectorsDegrees(shellVector,tempVector))*180/Math.PI;
                    biggestI = i;
                }
                else if((Math.acos(calculateVectorsDegrees(shellVector,tempVector))*180/Math.PI == biggestDegree)
                        && (calculateDistanceFromActualVectorApex(points.get(i),p1)) < (calculateDistanceFromActualVectorApex(points.get(biggestI),p1))){
                    biggestDegree = Math.acos(calculateVectorsDegrees(shellVector,tempVector))*180/Math.PI;
                    biggestI = i;
                }
            }
            shellPoints.add(points.get(biggestI));
            p0 = new Point(p1);
            p1 = points.get(biggestI);
            if(points.get(firstApexIndex).equals(p1)){
                break;
            }
            //break;
        }

        for (Point point: shellPoints) {
            System.out.println(point);
        }

        return shellPoints;
    }

    private int[] getVector(Point p1, Point p2){
        int tab[] = new int[2];
        tab[0] = p2.x - p1.x;
        tab[1] = p2.y - p1.y;
        return tab;
    }

    private double calculateVectorLength(int vector[]){
        return Math.sqrt(Math.pow(vector[0],2) + Math.pow(vector[1],2));
    }

    private double calculateVectorsDegrees(int vector1[], int vector2[]){
        return (vector1[0] * vector2[0] + vector1[1] * vector2[1]) / (calculateVectorLength(vector1) * calculateVectorLength(vector2));
    }

    private int findFirstApexIndex(){
        int lowestY = points.get(0).y;
        int lowestX = points.get(0).x;
        int firstApexIndex = 0;

        for(int i = 0; i < points.size(); ++i){
            if(points.get(i).y < lowestY || (points.get(i).y == lowestY && points.get(i).x < lowestX)){
                lowestX = points.get(i).x;
                lowestY = points.get(i).y;
                firstApexIndex = i;
            }
        }
        return firstApexIndex;
    }

    private static double calculateDistanceFromActualVectorApex(Point point, Point actualVectorStartPoint){
        return Math.sqrt(Math.pow((point.x-actualVectorStartPoint.x),2)+Math.pow((point.y-actualVectorStartPoint.y),2));
    }
}
