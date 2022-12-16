package Multithreading.Semaphore;

/**
 * Main class for assignment 2.
 */
public class Main {

  /**
   * Main method for assignment 2.
   *
   * @param args .
   */
  public static void main(String[] args) {

    Task1 task1 = new Task1();
    task1.run();

    System.out.println("-----------------");

    Task2 task2 = new Task2();
    task2.run();

    System.out.println("-----------------");

    Task3 task3 = new Task3();
    task3.run();
  }
}
