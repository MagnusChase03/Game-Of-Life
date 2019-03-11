import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    private GameOfLife() {
    
        setup();
    
    }
    
    private void setup() {
    
        setTitle("The Game Of Life");
        setSize(600, 630);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    
        setVisible(true);
        
        Panel panel = new Panel();
        add(panel);
        panel.run();
    
    }

    public static void main(String[] args) {
    
        new GameOfLife();
    
    }

}

class Panel extends JPanel implements Runnable {

    private int[][] grid;

    Panel() {
    
        grid = new int[60][60];
        createGrid();
    
    }
    
    private void createGrid() {
    
        for (int i = 0; i < grid.length; i++) {
        
            for (int j = 0; j < grid[i].length; j++) {
            
                if (Math.random() <= .3) {
                    
                    grid[i][j] = 1;
                    
                }
                
            }
            
        }
          
    }
    
    public void run() {
    
        while (true) {
        
            try {
            
                Thread.sleep(1000);
                next();
                repaint();
            
            } catch (Exception e) {}
        
        }
    
    }
    
    private void next() {   
        
        int[][] nextGen = new int[grid.length][grid[0].length];
        
        for (int i = 0; i < grid.length; i++) {
        
            for (int j = 0; j < grid[i].length; j++) {
            
                int counter = 0;
                
                try {
                
                    for (int y = -1; y <= 1; y++) {
        
                        for (int x = -1; x <= 1; x++) {
                        
                            if (grid[i + y][j + x] == 1) {
                            
                                counter++;
                                
                            }
                        }
                        
                    }
                    
                } catch (IndexOutOfBoundsException e) {}
                    
                if (grid[i][j] == 1) {
                
                    counter--;
                
                }
                
                if (counter < 2 && grid[i][j] == 1) {
                
                    nextGen[i][j] = 0;
                    
                } else if (counter == 2 && grid[i][j] == 1 || counter == 3 && grid[i][j] == 1) {
                
                    nextGen[i][j] = 1;
                
                } else if (counter > 3 && grid[i][j] == 1) {
                
                    nextGen[i][j] = 0;
                
                } else if (counter == 3 && grid[i][j] == 0) {
                
                    nextGen[i][j] = 1;
                    
                }
                                                
            }
            
        }
        
        grid = nextGen;
           
    }

    public void paint(Graphics g) {
    
        g.setColor(Color.BLACK);
        g.clearRect(0, 0, 600, 630);
        for (int i = 0; i < grid.length; i++) {
        
            for (int j = 0; j < grid[i].length; j++) {
            
                if (grid[i][j] == 1) {
                    
                    g.fillRect(i * 10, j * 10, 10, 10);
                }
            
            }
            
        }
    
    }

}
