package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadsPoolPract1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new ProcWork(i));
        }
        executorService.shutdown();

        System.out.println("main work ...");

        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

class ProcWork implements Runnable {
    private int id;

    public ProcWork(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread " + id + " work ...");
    }
}


