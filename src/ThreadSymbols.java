public class ThreadSymbols {
    static class SymbolPrinter implements Runnable {
        private final char symbol;

        public SymbolPrinter(char symbol) {
            this.symbol = symbol;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.print(symbol);
            }
        }
    }

    public static void main(String[] args) {
        Thread dashThread = new Thread(new SymbolPrinter('-'));
        Thread pipeThread = new Thread(new SymbolPrinter('|'));

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