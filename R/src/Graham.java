import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Graham {

    private ArrayList<PointAndDegree> pointsAndDegrees = new ArrayList<>();
    private int firstApexIndex;

    public Graham() {
    }

    public ArrayList<PointAndDegree> runGraham(ArrayList<Point> points){
        pointsToPointsAndDegrees(points);

        firstApexIndex = findFirstApexIndex();
        setFirstApexFirstOnPointsAndDegreesList();

        setPointsDegreesFromFirstPoint();
        sortPointsAndDegrees();
        pointsAndDegrees.add(pointsAndDegrees.get(0));
        //printPointsAndDegrees();

        ArrayList<PointAndDegree> shellApexes = new ArrayList<>();
        shellApexes.add(pointsAndDegrees.get(0));
        shellApexes.add(pointsAndDegrees.get(1));
        shellApexes.add(pointsAndDegrees.get(2));
        for(int i = 3; i < pointsAndDegrees.size(); ++i){
            //System.out.println(calculateMatrixDeterminant(shellApexes.get(shellApexes.size()-2).getPoint(),shellApexes.get(shellApexes.size()-1).getPoint(),pointsAndDegrees.get(i).getPoint()));
            while (calculateMatrixDeterminant(shellApexes.get(shellApexes.size()-2).getPoint(),shellApexes.get(shellApexes.size()-1).getPoint(),pointsAndDegrees.get(i).getPoint()) < 0){//krawedz po prawej
                shellApexes.remove(shellApexes.size()-1);
            }
            shellApexes.add(pointsAndDegrees.get(i));
            //System.out.println(shellApexes.size());
        }

        for (PointAndDegree pointAndDegree: shellApexes) {
            System.out.println(pointAndDegree.getPoint());
        }

        return shellApexes;
    }

    public ArrayList<PointAndDegree> getPointsAndDegrees() {
        return pointsAndDegrees;
    }

    public void setPointsAndDegrees(ArrayList<PointAndDegree> pointsAndDegrees) {
        this.pointsAndDegrees = pointsAndDegrees;
    }

    private void pointsToPointsAndDegrees(ArrayList<Point> points){
        pointsAndDegrees.clear();
        for (Point point: points) {
            pointsAndDegrees.add(new PointAndDegree(point,null));
        }
    }

    private double calculateMatrixDeterminant(Point point1, Point point2, Point point3){
        return point1.x * point2.y + point2.x * point3.y + point3.x * point1.y -
                point3.x*point2.y - point1.x * point3.y - point2.x * point1.y;
    }

    private void setFirstApexFirstOnPointsAndDegreesList(){
        PointAndDegree firstApex = pointsAndDegrees.get(firstApexIndex);
        pointsAndDegrees.remove(firstApexIndex);
        firstApex.setDegree(-1.0);
        pointsAndDegrees.add(0,firstApex);
        firstApexIndex = 0;
    }

    private void setPointsDegreesFromFirstPoint(){
        Point firstPoint = pointsAndDegrees.get(firstApexIndex).getPoint();
        for (int i = 1; i < pointsAndDegrees.size(); ++i) {
            pointsAndDegrees.get(i).setDegree(Math.atan2(pointsAndDegrees.get(i).getPoint().y-firstPoint.y,pointsAndDegrees.get(i).getPoint().x-firstPoint.x) * 180/ Math.PI);
        }
        pointsAndDegrees.get(firstApexIndex).setDegree(-1.0);
    }

    private int findFirstApexIndex(){
        int lowestY = pointsAndDegrees.get(0).getPoint().y;
        int lowestX = pointsAndDegrees.get(0).getPoint().x;
        int firstApexIndex = 0;
        for (int i = 0; i < pointsAndDegrees.size(); ++i){
            if(pointsAndDegrees.get(i).getPoint().y < lowestY || (pointsAndDegrees.get(i).getPoint().y == lowestY && pointsAndDegrees.get(i).getPoint().x < lowestX) ){
                lowestX = pointsAndDegrees.get(i).getPoint().x;
                lowestY = pointsAndDegrees.get(i).getPoint().y;
                firstApexIndex = i;
            }
        }
        return firstApexIndex;
    }

    private void printPointsAndDegrees(){
        for(int i = 0; i< pointsAndDegrees.size(); i++){
            System.out.println(pointsAndDegrees.get(i).getPoint() + " , Degree: " + pointsAndDegrees.get(i).getDegree() + " , Distance: " + calculateDistanceFromFirstApex(pointsAndDegrees.get(i).getPoint()));
        }
        System.out.println();
    }

    private void sortPointsAndDegrees(){
        Collections.sort(pointsAndDegrees, new Comparator<PointAndDegree>() {
            @Override
            public int compare(PointAndDegree o1, PointAndDegree o2) {
                if(o1.getDegree() == o2.getDegree() || o1.getDegree().equals(o2.getDegree())){
                    return calculateDistanceFromFirstApex(o1.getPoint()) < calculateDistanceFromFirstApex(o2.getPoint()) ? -1 : 1;
                }
                return o1.getDegree() < o2.getDegree() ? -1 : 1;
            }
        });
    }

    private double calculateDistanceFromFirstApex(Point point){
        Point firstApex = pointsAndDegrees.get(firstApexIndex).getPoint();
        return Math.sqrt(Math.pow((point.x-firstApex.x),2)+Math.pow((point.y-firstApex.y),2));
    }
}