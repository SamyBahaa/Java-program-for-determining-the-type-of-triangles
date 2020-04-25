import java.io.Serializable;

public class Point implements Serializable {
        float point_x=0;
        float point_y=0;

    public Point(float point_x, float point_y) {
        this.point_x = point_x;
        this.point_y = point_y;
    }

    public float getPoint_x() {
        return point_x;
    }

    public float getPoint_y() {
        return point_y;
    }
}
