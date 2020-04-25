import java.io.Serializable;

public class Triangle implements Serializable {
    private static final long serialVersionUID = 1L;
    Point P1;
    Point P2;
    Point P3;
    String name;

    public Triangle(Point p1, Point p2, Point p3, String name) {
        P1 = p1;
        P2 = p2;
        P3 = p3;
        this.name = name;
    }



}
