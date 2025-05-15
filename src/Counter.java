public class Counter {
    private int value = 0;

    public void  increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        
        // Создаем поток для увеличения счетчика
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });

        // Создаем поток для уменьшения счетчика
        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrement();
            }
        });

        // Запускаем оба потока
        incrementThread.start();
        decrementThread.start();

        // Ждем завершения обоих потоков
        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Выводим финальное значение счетчика
        System.out.println("Финальное значение счетчика: " + counter.getValue());
    }
} 