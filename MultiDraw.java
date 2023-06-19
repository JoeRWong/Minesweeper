import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class MultiDraw extends JPanel  implements MouseListener 
{
        int startX = 40;
        int startY = 40;
        int cellWidth = 40;
        int rows = 7;
        int cols = 7;
        Color[][] grid = new Color[rows][cols];
        Tile[][] squares = new Tile[rows][cols];
        boolean gameOver = false;
        boolean gg = false;
        public MultiDraw(Dimension dimension) 
        {
            setSize(dimension);
            setPreferredSize(dimension);
            addMouseListener(this);
            //initialize array
            for (int row = 0; row < grid.length; row++)
            {
                for (int col = 0; col < grid[0].length; col++) 
                {
                   if((col%2 == 0 || row%2 == 0) && !(col%2 == 0 && row%2 == 0))
                   {
                       grid[row][col] = new Color(0, 225, 70);
                   }
                   else
                   {
                       grid[row][col] = new Color(0, 185, 70);
                   }
                }
            }
            setMines();
        }

        public void paintComponent(Graphics g) 
        {
            Graphics2D g2 = (Graphics2D)g;
            Dimension d = getSize();
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0,0,d.width,d.height);
            startX = 40;
            startY = 40;
            if(!(gameOver||gg)){
                for(int i = 0; i < grid.length; i++)
                {
                    for(int j = 0;j < grid[0].length; j++)
                    {
                        g2.setColor(grid[i][j]);
                        g2.drawRect(startX, startY, cellWidth, cellWidth);
                        g2.fillRect(startX, startY, cellWidth, cellWidth);
                        startX += cellWidth+ 4;
                        if(squares[i][j].getShow() && numMines(j, i) > 0)
                        {
                            g2.setColor(new Color(0, 0, 0));
                            g2.setFont(new Font("calibri", Font.PLAIN, 35));
                            g2.drawString("" + numMines(j, i), startX-33, startY+31);
                        }
                    }
                    startX = cellWidth;
                    startY += cellWidth+4;
                }
            }
            else if(gg)
            {
                g2.setColor(new Color(0, 255, 0));
                g2.setFont(new Font("calibri", Font.PLAIN, 60));
                g2.drawString("YOU WON", 70, 200);
            }
            else
            {
                g2.setColor(new Color(255, 0, 0));
                g2.setFont(new Font("calibri", Font.PLAIN, 60));
                g2.drawString("YOU DIED", 70, 200);
            }
        }
        public void mousePressed(MouseEvent e) 
        {
            int x = (e.getX()-40)/44;
            int y = (e.getY()-40)/44;
            if(e.getButton() == MouseEvent.BUTTON1)
            {
                String string = "";
                clearTiles(x, y);
            }
            else
            {
                grid[y][x] = new Color(255, 0, 255);
            }
            int changeCount = 0;
            for(int i = 0; i < squares.length; i++)
            {
                for(int j = 0; j < squares[i].length; j++)
                {
                    if(squares[i][j].getChanged())
                    {
                        changeCount++;
                    }
                }
            }
            if((rows*cols) - changeCount == 8)
            {
                gg = true;
            }
            repaint();
        }
        
        public void numPrint(int x, int y){
            
        }
        
        public void mouseReleased(MouseEvent e) 
        {

        }

        public void mouseEntered(MouseEvent e) 
        {

        }

        public void mouseExited(MouseEvent e) 
        {

        }

        public void mouseClicked(MouseEvent e) 
        {

        }
        
        public void setMines()
        {
            for(int j = 0; j <rows; j++)
            {
                for(int k = 0; k<cols; k++)
                {
                    squares[j][k] = new Tile(false);
                }
            }
            int i = 0;
            while(i < 9)
            {
                int y = (int) (Math.random()*(rows));
                int x = (int) (Math.random() *(cols));
                if(squares[x][y].getMine() == false)
                {
                    squares[x][y].setMine();
                    i++;
                }
            }
        }
        
        public int numMines(int x, int y)
        {
            int counter = 0;
            if(x == 0 && y == 0)
            {
                if(squares[y+1][x+1].getMine())
                {
                    counter++;
                }
                if(squares[y+1][x].getMine())
                {
                    counter++;
                }
                if(squares[y][x+1].getMine())
                {
                    counter++;
                }
                return counter;
            }
            else if(x == squares[0].length-1 && y == squares.length-1)
            {
                if(squares[y-1][x-1].getMine())
                {
                    counter++;
                }
                if(squares[y-1][x].getMine())
                {
                    counter++;
                }
                if(squares[y][x-1].getMine())
                {
                    counter++;
                }
                return counter;
            }
            else if(x == squares[0].length -1 && y == 0)
            {
                if(squares[y+1][x-1].getMine())
                {
                    counter++;
                }
                if(squares[y+1][x].getMine())
                {
                    counter++;
                }
                if(squares[y][x-1].getMine())
                {
                    counter++;
                }
                return counter;
            }
            else if(x == 0 && y == squares.length-1)
            {
                if(squares[y-1][x+1].getMine())
                {
                    counter++;
                }
                if(squares[y-1][x].getMine())
                {
                    counter++;
                }
                if(squares[y][x+1].getMine())
                {
                    counter++;
                }
                return counter;
            }
            else if(y == 0)
            {
                if(squares[y+1][x].getMine())
                {
                    counter++;
                }
                if(squares[y+1][x+1].getMine())
                {
                    counter++;
                }
                if(squares[y+1][x-1].getMine())
                {
                    counter++;
                }
                if(squares[y][x-1].getMine())
                {
                    counter++;
                }
                if(squares[y][x+1].getMine())
                {
                    counter++;
                }
                return counter;
            }
            else if(y == squares.length-1)
            {
                if(squares[y-1][x].getMine())
                {
                    counter++;
                }
                if(squares[y-1][x+1].getMine())
                {
                    counter++;
                }
                if(squares[y-1][x-1].getMine())
                {
                    counter++;
                }
                if(squares[y][x-1].getMine())
                {
                    counter++;
                }
                if(squares[y][x+1].getMine())
                {
                    counter++;
                }
                return counter;
            }
            else if(x == 0)
            {
                if(squares[y-1][x].getMine())
                {
                    counter++;
                }
                if(squares[y+1][x].getMine())
                {
                    counter++;
                }
                if(squares[y+1][x+1].getMine())
                {
                    counter++;
                }
                if(squares[y-1][x+1].getMine())
                {
                    counter++;
                }
                if(squares[y][x+1].getMine())
                {
                    counter++;
                }
                return counter;
            }
            else if(x == squares[0].length - 1)
            {
                if(squares[y-1][x].getMine())
                {
                    counter++;
                }
                if(squares[y+1][x].getMine())
                {
                    counter++;
                }
                if(squares[y+1][x-1].getMine())
                {
                    counter++;
                }
                if(squares[y-1][x-1].getMine())
                {
                    counter++;
                }
                if(squares[y][x-1].getMine())
                {
                    counter++;
                }
                return counter;
            }
            else
            {
                if(squares[y-1][x].getMine())
                {
                    counter++;
                }
                if(squares[y+1][x].getMine())
                {
                    counter++;
                }
                if(squares[y+1][x-1].getMine())
                {
                    counter++;
                }
                if(squares[y-1][x-1].getMine())
                {
                    counter++;
                }
                if(squares[y][x-1].getMine())
                {
                    counter++;
                }
                if(squares[y+1][x+1].getMine())
                {
                    counter++;
                }
                if(squares[y-1][x+1].getMine())
                {
                    counter++;
                }
                if(squares[y][x+1].getMine())
                {
                    counter++;
                }
            }
            if(squares[y][x].getMine())
            {
                counter = -1;
            }
            return counter;
        }
        public void clearTiles(int x, int y)
        {
            if(!(squares[y][x].getMine())){
                grid[y][x] = new Color(0, 0, 255);
                squares[y][x].setChanged();
                squares[y][x].setShow();
            }
            if(squares[y][x].getMine())
            {
                grid[y][x] = new Color(255, 0, 0);
                gameOver = true;
            }
            if(x+1 == cols && y + 1 == rows && !(squares[y][x].getMine()))
            {
                if(numMines(x, y) == 0)
                {
                    if(!(squares[y-1][x-1].getChanged()))
                    {
                        clearTiles(x-1, y-1);
                    }
                    if(!(squares[y-1][x].getChanged()))
                    {
                        clearTiles(x, y-1);
                    }
                    if(!(squares[y][x-1].getChanged()))
                    {
                        clearTiles(x-1, y);
                    }
                }
            }
            else if(x == 0 && y+1 == rows && !(squares[y][x].getMine()))
            {
                if(numMines(x, y) == 0)
                {
                    if(!(squares[y-1][x+1].getChanged()))
                    {
                        clearTiles(x+1, y-1);
                    }
                    if(!(squares[y-1][x].getChanged()))
                    {
                        clearTiles(x, y-1);
                    }
                    if(!(squares[y][x+1].getChanged()))
                    {
                        clearTiles(x+1, y);
                    }
                }
            }
            else if(x == 0 && y == 0 && !(squares[y][x].getMine()))
            {
                if(numMines(x, y) == 0)
                {
                    if(!(squares[y+1][x+1].getChanged()))
                    {
                        clearTiles(x+1, y+1);
                    }
                    if(!(squares[y+1][x].getChanged()))
                    {
                        clearTiles(x, y+1);
                    }
                    if(!(squares[y][x+1].getChanged()))
                    {
                        clearTiles(x+1, y);
                    }
                }
            }
            else if(x+1 == cols && y == 0 && !(squares[y][x].getMine()))
            {
                if(numMines(x, y) == 0)
                {
                    if(!(squares[y+1][x-1].getChanged()))
                    {
                        clearTiles(x-1, y+1);
                    }
                    if(!(squares[y+1][x].getChanged()))
                    {
                        clearTiles(x, y+1);
                    }
                    if(!(squares[y][x-1].getChanged()))
                    {
                        clearTiles(x-1, y);
                    }
                }
            }
            else if(x+1 == cols && !(squares[y][x].getMine()))
            {
                if(numMines(x, y) == 0)
                {
                    if(!(squares[y][x-1].getChanged()))
                    {
                        clearTiles(x-1, y);
                    }
                    if(!(squares[y-1][x].getChanged()))
                    {
                        clearTiles(x, y-1);
                    }
                    if(!(squares[y+1][x].getChanged()))
                    {
                        clearTiles(x, y+1);
                    }
                    if(!(squares[y-1][x-1].getChanged()))
                    {
                        clearTiles(x-1, y-1);
                    }
                    if(!(squares[y+1][x-1].getChanged()))
                    {
                        clearTiles(x-1, y+1);
                    }
                }
            }
            else if(x == 0)
            {
                if(numMines(x, y) == 0 && !(squares[y][x].getMine()))
                {
                    if(!(squares[y][x+1].getChanged()))
                    {
                        clearTiles(x+1, y);
                    }
                    if(!(squares[y-1][x].getChanged()))
                    {
                        clearTiles(x, y-1);
                    }
                    if(!(squares[y+1][x].getChanged()))
                    {
                        clearTiles(x, y+1);
                    }
                    if(!(squares[y-1][x+1].getChanged()))
                    {
                        clearTiles(x+1, y-1);
                    }
                    if(!(squares[y+1][x+1].getChanged()))
                    {
                        clearTiles(x+1, y+1);
                    }
                }
            }
            else if(x+1 == cols)
            {
                if(numMines(x, y) == 0 && !(squares[y][x].getMine()))
                {
                    if(!(squares[y][x-1].getChanged()))
                    {
                        clearTiles(x-1, y);
                    }
                    if(!(squares[y-1][x].getChanged()))
                    {
                        clearTiles(x, y-1);
                    }
                    if(!(squares[y+1][x].getChanged()))
                    {
                        clearTiles(x, y+1);
                    }
                    if(!(squares[y-1][x-1].getChanged()))
                    {
                        clearTiles(x+1, y-1);
                    }
                    if(!(squares[y+1][x-1].getChanged()))
                    {
                        clearTiles(x+1, y+1);
                    }
                }
            }
            else if(y+1 == rows)
            {
                if(numMines(x, y) == 0 && !(squares[y][x].getMine()))
                {
                    if(!(squares[y-1][x].getChanged()))
                    {
                        clearTiles(x, y-1);
                    }
                    if(!(squares[y][x+1].getChanged()))
                    {
                        clearTiles(x+1, y);
                    }
                    if(!(squares[y][x-1].getChanged()))
                    {
                        clearTiles(x-1, y);
                    }
                    if(!(squares[y-1][x-1].getChanged()))
                    {
                        clearTiles(x-1, y-1);
                    }
                    if(!(squares[y-1][x+1].getChanged()))
                    {
                        clearTiles(x-1, y+1);
                    }
                }
            }
            else if(y == 0)
            {
                if(numMines(x, y) == 0 && !(squares[y][x].getMine()))
                {
                    if(!(squares[y+1][x].getChanged()))
                    {
                        clearTiles(x, y+1);
                    }
                    if(!(squares[y][x+1].getChanged()))
                    {
                        clearTiles(x+1, y);
                    }
                    if(!(squares[y][x-1].getChanged()))
                    {
                        clearTiles(x-1, y);
                    }
                    if(!(squares[y+1][x-1].getChanged()))
                    {
                        clearTiles(x-1, y+1);
                    }
                    if(!(squares[y+1][x+1].getChanged()))
                    {
                        clearTiles(x+1, y+1);
                    }
                }
            }
            else
            {
                if(numMines(x, y) == 0)
                {
                    if(!(squares[y][x+1].getChanged()))
                    {
                        clearTiles(x+1, y);
                    }
                    if(!(squares[y][x-1].getChanged()))
                    {
                        clearTiles(x-1, y);
                    }
                    if(!(squares[y+1][x].getChanged()))
                    {
                        clearTiles(x, y+1);
                    }
                    if(!(squares[y-1][x].getChanged()))
                    {
                        clearTiles(x, y-1);
                    }
                    if(!(squares[y+1][x+1].getChanged()))
                    {
                        clearTiles(x+1, y+1);
                    }
                    if(!(squares[y+1][x-1].getChanged()))
                    {   
                        clearTiles(x-1, y+1);
                    }
                    if(!(squares[y-1][x+1].getChanged()))
                    {
                        clearTiles(x+1, y-1);
                    }
                    if(!(squares[y-1][x-1].getChanged()))
                    {
                        clearTiles(x-1, y-1);
                    }
                }
            }
        }
    }