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
        JButton buttonExperiment = new JButton("Експеримент");
        JButton buttonJoinDemo = new JButton("Демонстрация join()");

        buttonStart.addActionListener(e -> {
            Ball b = new Ball(canvas);
            canvas.add(b);
            BallThread thread = new BallThread(b);
            thread.start();
            System.out.println("Thread name = " + thread.getName());
        });

        buttonExperiment.addActionListener(e -> {
            Ball redBall = new Ball(canvas, Color.RED, true);
            canvas.add(redBall);
            BallThread redThread = new BallThread(redBall);
            redThread.start();

            for (int i = 0; i < 1000; i++) {
                Ball blueBall = new Ball(canvas, Color.BLUE, true);
                canvas.add(blueBall);
                BallThread blueThread = new BallThread(blueBall);
                blueThread.start();
            }
        });

        buttonJoinDemo.addActionListener(e -> {
            Ball redBall = new Ball(canvas, Color.RED);
            canvas.add(redBall);
            BallThread redThread = new BallThread(redBall);
            redThread.setName("Red Ball Thread");
            
            Ball blueBall = new Ball(canvas, Color.BLUE);
            canvas.add(blueBall);
            BallThread blueThread = new BallThread(blueBall);
            blueThread.setName("Blue Ball Thread");

            blueThread.start();
            
            try {
                System.out.println("Blue Ball Thread is running...");
                blueThread.join();
                System.out.println("Blue Ball Thread is finished, starting red ball");
                redThread.start();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });

        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonExperiment);
        buttonPanel.add(buttonJoinDemo);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}

