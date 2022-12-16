package Multithreading.Semaphore;

import java.util.concurrent.Semaphore;

public class CircularQueue implements IMessageQueue{

    private int size = 5;
    private int front, rear;
    private char messages[];
    public Semaphore enqueueSemaphore;
    public Semaphore dequeueSemaphore;

    public CircularQueue(int size) {
        this.size = size;
        messages = new char[size];
        front = rear = -1;
    
        // initialize the semaphores with the maximum number of permits equal to the size of the queue
        enqueueSemaphore = new Semaphore(size);
        dequeueSemaphore = new Semaphore(0);
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        if((rear - front == size - 1) || (front - rear == 1)) {
            return true;
        } else {
            return false;
        }
    }

    public char removeMessage() {

        if(rear == front) { // If the last element is deleted.
            char temp = messages[rear];
            front = rear = -1;
            return temp;
        } else {
            char msg = messages[front];
            front = front++ % size;
            enqueueSemaphore.release();
            return msg;
        }

    }

    @Override
    public boolean Send(char msg) {
        if(isFull()) {
            dequeueSemaphore.release();
            return false;
        } else if (isEmpty()){
            rear = front = 0;
            messages[0] = msg;
            enqueueSemaphore.release();
            return true;

        } else {

            rear = rear++ % size;
            messages[rear] = msg;
            dequeueSemaphore.release();
            return true;
        }
    }

    @Override
    public char Recv() {
        if (isEmpty()) {
            enqueueSemaphore.release();
        } else {
            return removeMessage();
        }
        // if queue is empty then block send something..
        return 0;
    }
    
}
