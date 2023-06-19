import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Minesweepered {
    public static void main(String[] argv) {
        JFrame frame = new JFrame("Minesweeper");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        frame.add(new MultiDraw(frame.getSize()));
        frame.pack();
        frame.setVisible(true);
    }   
}