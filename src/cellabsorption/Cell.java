package cellabsorption;
import java.awt.Canvas;
import java.awt.Color;
import java.util.Random;
import edu.macalester.graphics.CanvasWindow;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

public class Cell {
    //x, y radius, color
    private double x;
    private double y;
    private double radius; 
    private Color color;

    private static final double
    WIGGLINESS = 0.2,
    WANDER_FROM_CENTER = 60000;

    private double direction;
    
    private Ellipse cellShape;
    public Cell(double x, double y, double radius, Color color){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.direction = normalizeRadians(Math.random() * Math.PI * 2);

        cellShape = new Ellipse(x, y, radius * 2, radius * 2);
        cellShape.setFillColor(color);
    }

    public void grow(double amount) {
        setRadius(radius + amount);
    }
    private void setRadius(double newRadius) {
        if (newRadius < 0) {
            newRadius = 0;
        }
        this.radius = newRadius;
        Point previousCenter = cellShape.getCenter();
        cellShape.setSize(new Point(newRadius * 2, newRadius * 2));
        cellShape.setCenter(previousCenter);
    }
    
    public void moveAround(Point centerOfGravity) {
        cellShape.moveBy(Math.cos(direction), Math.sin(direction));

        double distToCenter = cellShape.getCenter().distance(centerOfGravity);
        double angleToCenter = centerOfGravity.subtract(cellShape.getCenter()).angle();
        double turnTowardCenter = normalizeRadians(angleToCenter - direction);

        direction = normalizeRadians(
            direction
                + (Math.random() - 0.5) * WIGGLINESS
                + turnTowardCenter * Math.tanh(distToCenter / WANDER_FROM_CENTER));
    }

    private static double sqr(double x) {
        return x * x;
    }

    private static double normalizeRadians(double theta) {
        double pi2 = Math.PI * 2;
        return ((theta + Math.PI) % pi2 + pi2) % pi2 - Math.PI;
    }
   
    public void addToCanvas(CanvasWindow canvas){
        canvas.add(cellShape);
    }
}
