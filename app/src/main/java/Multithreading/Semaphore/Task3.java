package Multithreading.Semaphore;

/**
 * Class to execute task3.
 */
public class Task3 {

  /**
   * Method to execute task3.
   */
  public void run() {

    boolean run = true;
    CircularQueue queue = new CircularQueue(5);

    Thread sA = new Thread(() -> {
      while (run == true) {
        queue.send('A');
      }
    });

    Thread sB = new Thread(() -> {
      while (run == true) {
        queue.send('B');
      }
    });

    Thread sC = new Thread(() -> {
      while (run == true) {
        queue.send('C');
      }
    });

    Thread reciever = new Thread(() -> {
      while (run == true) {
        System.out.print(queue.recv());
      }
    });

    sA.start();
    sB.start();
    sC.start();

    reciever.start();

  }

}
