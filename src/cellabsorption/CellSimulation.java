package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {
    
    private CanvasWindow canvas;
    private Random rand = new Random();
    private List<Cell> cells;
    

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        double size = rand.nextInt(5) + 2;
        cells = new ArrayList<Cell>();
        populateCells();
        


       

        //noinspection InfiniteLoopStatement
        while (true) {
            for(Cell cell: cells) {
                Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
                cell.moveAround(canvasCenter);
                handleCellInteraction();
                
            }
            
            canvas.draw();
            canvas.pause(10);
        }
    }

    private void populateCells() {
        
        
        for(int i = 0 ; i<200; i++){
            double size = rand.nextInt(5) + 2;
            Cell cell = new Cell(
            rand.nextDouble() * (canvas.getWidth() - size),
            rand.nextDouble() * (canvas.getWidth() - size),
            size,
            Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
            cell.addToCanvas(canvas); 
            cells.add(cell);      
        }
    }

    private void handleCellInteraction() {
            for(int i = 0; i < 200; i++){
                Cell currCell = cells.get(i);
                for(int j = i + 1; j < cells.size(); j++){
                    Cell currCell2 = cells.get(j);
                    currCell.interactWith(currCell2);
                }
            }
        
        
    }


   

   

    

    
    
}
