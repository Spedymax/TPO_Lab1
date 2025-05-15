import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

class Ball {
    private final Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private final Color color;
    private boolean inPocket = false;
    

    private static final int POCKET_WIDTH = 60;
    private static final int POCKET_HEIGHT = 60;
    private static final int EXPERIMENT_SPEED = 1;

    public Ball(Component c, Color color, boolean isExperiment) {
        this.canvas = c;
        this.color = color;

        if (isExperiment) {
            x = 50;
            y = 50;
            dx = EXPERIMENT_SPEED;
            dy = EXPERIMENT_SPEED;
        } else {
            if (Math.random() < 0.5) {
                x = new Random().nextInt(this.canvas.getWidth());
                y = 0;
            } else {
                x = 0;
                y = new Random().nextInt(this.canvas.getHeight());
            }
            dx = 1 + new Random().nextInt(5);
            dy = 1 + new Random().nextInt(5);
        }
    }

    public Ball(Component c, Color color) {
        this(c, color, false);
    }

    public Ball(Component c) {
        this(c, new Color(
            new Random().nextFloat(),
            new Random().nextFloat(),
            new Random().nextFloat()
        ));
    }

    public void draw(Graphics2D g2) {
        if (!inPocket) {
            g2.setColor(color);
            g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
        }
    }

    public Color getColor() {
        return color;
    }

    public boolean move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }

        int pocketX = this.canvas.getWidth() / 2 - POCKET_WIDTH / 2;
        int pocketY = this.canvas.getHeight() / 2 - POCKET_HEIGHT / 2;
        
        if (x >= pocketX && x + XSIZE <= pocketX + POCKET_WIDTH && 
            y >= pocketY && y + YSIZE <= pocketY + POCKET_HEIGHT) {
            inPocket = true;
            BounceFrame.incrementPocketCount();
            return false;
        }
        
        this.canvas.repaint();
        return true;
    }
}
