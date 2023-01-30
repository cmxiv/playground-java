package dev.tandosid.playground.java.deadlock;

interface Printer {
    void print(String string);
}

public class CreateDeadlock {

    private final String resource1 = "resource-1";
    private final String resource2 = "resource-2";

    public void deadlock(Printer printer) {
        final var thread1 = new Thread(() -> {
            synchronized (resource1) {
                printer.print(Thread.currentThread().getName() + " Taking lock on " + resource1);
                try {
                    Thread.sleep(100L);
                } catch (Exception e) {}
                synchronized (resource2) {
                    printer.print(Thread.currentThread().getName() + " Taking lock on " + resource2);
                }
            }
        });

        final var thread2 = new Thread(() -> {
            synchronized (resource2) {
                printer.print(Thread.currentThread().getName() + " Taking lock on " + resource2);
                synchronized (resource1) {
                    printer.print(Thread.currentThread().getName() + " Taking lock on " + resource1);
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {}
    }

}
