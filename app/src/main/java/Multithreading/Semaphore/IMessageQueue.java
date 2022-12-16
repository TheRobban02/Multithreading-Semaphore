package Multithreading.Semaphore;

/**
 * Interface for the message queue.
 */
public interface IMessageQueue {

  /**
   * Send a message.
   *
   * @return true if successful, otherwise false.
   */
  boolean send(char msg);

  /**
   * Receive a message.
   * If queue has no message, Recv() will block until one arrives.
   *
   * @return A message.
   */
  char recv();
}
