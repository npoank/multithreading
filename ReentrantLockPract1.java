package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPract1 {
    public static void main(String[] args) throws InterruptedException {
        CountWork countWork = new CountWork();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                countWork.incrementThread1();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                countWork.incrementThread2();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        countWork.showResult();
    }
}

class CountWork {
    private int value;
    private Lock lock = new ReentrantLock();

    private void count() {
        for (int i = 0; i < 10000; i++) {
            value++;
        }
    }

    public void incrementThread1() {
        lock.lock();
        count();
        lock.unlock();
    }

    public void incrementThread2() {
        lock.lock();
        count();
        lock.unlock();
    }

    public void showResult() {
        System.out.println(value);
    }

}
