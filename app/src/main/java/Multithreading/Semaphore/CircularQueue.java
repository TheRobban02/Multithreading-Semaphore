package Multithreading.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * Class that has an array as a circular queue.
 */
public class CircularQueue implements IMessageQueue {

  private int size;
  private int front;
  private int rear;
  private char[] messages;
  public Semaphore enqueueSemaphore;
  public Semaphore dequeueSemaphore;

  /**
   * Semaphore for adding and removing elements from array.
   *
   * @param size the size of the array.
   */
  public CircularQueue(int size) {
    this.size = size;
    messages = new char[size];
    front = rear = 0;

    // initialize the semaphores with the maximum number of permits equal to the
    // size of the queue
    enqueueSemaphore = new Semaphore(size);
    dequeueSemaphore = new Semaphore(0);
  }

  public boolean isEmpty() {
    return front == 0;
  }

  /**
   * Method to remove the first element of the array.
   * Uses FIFO.
   *
   * @return returns the removed char wich will later be printed.
   */
  public char removeMessage() {

    if (rear == front) { // If the last element is deleted.
      char temp = messages[rear];
      front = rear = 0; // Resetting the array.
      return temp;
    } else {
      char msg = messages[front];
      front = front++ % size;
      return msg;
    }

  }

  @Override
  public boolean send(char msg) {

    try {
      enqueueSemaphore.acquire();
    } catch (InterruptedException e) {}

    if (isEmpty()) {
      messages[0] = msg;
      dequeueSemaphore.release();
      return true;
    } else {
      rear = rear++ % size;
      messages[rear] = msg;
      dequeueSemaphore.release();
      return true;
    }
  }

  @Override
  public char recv() {

    try {
      dequeueSemaphore.acquire();
    } catch (InterruptedException e1) {}

    enqueueSemaphore.release();
    return removeMessage();

  }

}
