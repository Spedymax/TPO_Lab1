public class ThreadSymbols {
    private static final Object lock = new Object();
    private static boolean isDashTurn = true;

    static class SymbolPrinter implements Runnable {
        private final char symbol;
        private final boolean isDash;

        public SymbolPrinter(char symbol, boolean isDash) {
            this.symbol = symbol;
            this.isDash = isDash;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                synchronized (lock) {
                    while (isDash != isDashTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    System.out.print(symbol);
                    isDashTurn = !isDashTurn;
                    lock.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread dashThread = new Thread(new SymbolPrinter('-', true));
        Thread pipeThread = new Thread(new SymbolPrinter('|', false));

        dashThread.start();
        pipeThread.start();

        try {
            dashThread.join();
            pipeThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}