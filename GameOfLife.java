import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private boolean running;

    Panel() {
    
        setFocusable(true);
        grid = new int[30][30];
        running = true;
        
        addKeyListener(new Keyboard(this));
        
        createGrid();
    
    }
    
    public void createGrid() {
    
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
            
                Thread.sleep(500);
                
                if (running) {
                
                    next();
                    repaint();
                    
                }
            
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
        g.fillRect(0, 0, 600, 630);
        for (int i = 0; i < grid.length; i++) {
        
            for (int j = 0; j < grid[i].length; j++) {
            
                if (grid[i][j] == 1) {
                    
                    g.setColor(new Color(0, (int) (Math.random() * 171 + 30), (int) (Math.random() * 171 + 30)));
                    g.fillRect(i * 20, j * 20, 20, 20);
                }
            
            }
            
        }
    
    }
    
    public void setRunning(boolean r) {
    
        running = r;
    
    }
    
    public boolean getRunning() {
    
        return running;
    
    }

}

class Keyboard extends KeyAdapter {

    private Panel panel;

    Keyboard(Panel p) {
    
        panel = p;
    
    }
    
    public void keyPressed(KeyEvent e) {
    
        char key = (char) e.getKeyCode();

        switch (key) {
        
            case 'R':
                panel.createGrid();
                break;
                
            case ' ':
                panel.setRunning(!panel.getRunning());
                break;
        
        }
    
    }

}
