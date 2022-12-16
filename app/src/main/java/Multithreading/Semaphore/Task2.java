package Multithreading.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * Class to execute task2.
 */
public class Task2 {

  /**
   * Method to execute task2.
   */
  public void run() {

    // Create the semaphores
    Semaphore sa = new Semaphore(1);
    Semaphore sb = new Semaphore(0);
    Semaphore sc = new Semaphore(0);
    Semaphore sd = new Semaphore(0);

    // Create the threads
    Thread ta = new Thread(() -> {
      // i < 10 since there are 2 A:s per line
      for (int i = 0; i < 10; i++) {
        try {
          sa.acquire();
          if (Shared.counter == 2) {
            System.out.print("A");
            Shared.counter++;
            sc.release();
          } else {
            System.out.print("A");
            Shared.counter++;
            sb.release();
          }

        } catch (InterruptedException e) {}
      }
    });

    Thread tb = new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        try {
          sb.acquire();
          System.out.print("B");
          Shared.counter++;
          sa.release();

        } catch (InterruptedException e) {}
      }
    });

    Thread tc = new Thread(() -> {
      // i < 10 since there are 2 C:s per line
      for (int i = 0; i < 10; i++) {
        try {
          sc.acquire();
          if (Shared.counter == 5) {
            System.out.print("C\n");
            Shared.counter = 0;
            sa.release();
          } else {
            System.out.print("C");
            Shared.counter++;
            sd.release();

          }

        } catch (InterruptedException e) {}
      }
    });

    Thread td = new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        try {
          sd.acquire();
          System.out.print("D");
          Shared.counter++;
          sc.release();
        } catch (InterruptedException e) {}
      }
    });

    // Start the threads
    ta.start();
    tb.start();
    tc.start();
    td.start();

    try {

      ta.join();
      tb.join();
      tc.join();
      td.join();

    } catch (Exception e) {}
  }
}
