public class BallThread extends Thread {
    final Ball b;

    public BallThread(Ball ball){
        b = ball;
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

