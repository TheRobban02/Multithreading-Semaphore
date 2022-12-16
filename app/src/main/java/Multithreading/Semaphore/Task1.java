package Multithreading.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * Class that executes task1.
 */
public class Task1 {

  /**
   * Method to run task1.
   */
  public void run() {
    // Create the semaphores
    Semaphore sa = new Semaphore(1);
    Semaphore sb = new Semaphore(0);

    // Create the threads
    Thread ta = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          sa.acquire();
          System.out.print("A");
          sb.release();
        } catch (InterruptedException e) {}
      }
    });

    Thread tb = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          sb.acquire();
          System.out.print("B\n");
          sa.release();
        } catch (InterruptedException e) {}
      }
    });

    // Start the threads
    ta.start();
    tb.start();

    try {
      ta.join();
      tb.join();
    } catch (InterruptedException e) {}
  }

}
