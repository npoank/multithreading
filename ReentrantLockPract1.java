package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPract1 {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.workthread1();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.workthread2();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        task.showInfo();

    }
}

class Task {
    private int value;
    private Lock lock = new ReentrantLock();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            value++;
        }
    }

    public void workthread1() {
        lock.lock();
        increment();
        lock.unlock();
    }

    public void workthread2() {
        lock.lock();
        increment();
        lock.unlock();
    }

    public void showInfo() {
        System.out.println(value);
    }

}
