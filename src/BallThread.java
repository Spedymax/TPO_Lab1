import java.awt.Color;

public class BallThread extends Thread {
    final Ball b;
    private static final int RED_BALL_PRIORITY = Thread.MAX_PRIORITY;
    private static final int BLUE_BALL_PRIORITY = Thread.MIN_PRIORITY;

    public BallThread(Ball ball){
        b = ball;
        if (ball.getColor().equals(Color.RED)) {
            setPriority(RED_BALL_PRIORITY);
        } else if (ball.getColor().equals(Color.BLUE)) {
            setPriority(BLUE_BALL_PRIORITY);
        }
    }
    
    @Override
    public void run(){
        try{
            for(int i=1; i<10000; i++){

                if(!b.move()) {
                    System.out.println("Ball fell into pocket! Thread " 
                            + Thread.currentThread().getName() + " is terminating");
                    break;
                }
                System.out.println("Thread name = "
                        + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        } catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
}

