import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {

    private final BallCanvas canvas;
    private static JLabel pocketCountLabel;
    private static int pocketCount;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    

    public static synchronized void incrementPocketCount() {
        pocketCount++;
        SwingUtilities.invokeLater(() -> {
            if (pocketCountLabel != null) {
                pocketCountLabel.setText("Кульок у лузі: " + pocketCount);
            }
        });
    }

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");

        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);


        pocketCount = 0;
        pocketCountLabel = new JLabel("Кульок у лузі: 0");
        buttonPanel.add(pocketCountLabel);
        
        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        buttonStart.addActionListener(e -> {

            Ball b = new Ball(canvas);
            canvas.add(b);

            BallThread thread = new BallThread(b);
            thread.start();
            System.out.println("Thread name = " + thread.getName());
        });

        buttonStop.addActionListener(e -> System.exit(0));


        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}

