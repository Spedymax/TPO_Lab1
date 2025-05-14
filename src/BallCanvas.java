
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel{
    private final ArrayList<Ball> balls = new ArrayList<>();


    private static final int POCKET_WIDTH = 60;
    private static final int POCKET_HEIGHT = 60;
    
    public void add(Ball b){
        this.balls.add(b);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        

        g2.setColor(Color.BLACK);
        int pocketX = getWidth() / 2 - POCKET_WIDTH / 2;
        int pocketY = getHeight() / 2 - POCKET_HEIGHT / 2;
        g2.fillOval(pocketX, pocketY, POCKET_WIDTH, POCKET_HEIGHT);


        for(int i=0; i<balls.size();i++){
            Ball b = balls.get(i);
            b.draw(g2);
        }
    }
}

