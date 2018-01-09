import java.awt.*;

public class PointAndDegree {

    private Point point;
    private Double degree;

    public PointAndDegree(Point point, Double degree) {
        this.point = point;
        this.degree = degree;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Double getDegree() {
        return degree;
    }

    public void setDegree(Double degree) {
        this.degree = degree;
    }
}