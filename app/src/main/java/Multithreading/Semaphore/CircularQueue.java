package Multithreading.Semaphore;

import java.util.concurrent.Semaphore;

public class CircularQueue implements IMessageQueue{

    private int size = 5;
    private int head, tail;
    private char messages[];
    public Semaphore enqueueSemaphore;
    public Semaphore dequeueSemaphore;

    public CircularQueue(int size) {
        this.size = size;
        messages = new char[size];
        head = tail = 0;
    
        // initialize the semaphores with the maximum number of permits equal to the size of the queue
        enqueueSemaphore = new Semaphore(size);
        dequeueSemaphore = new Semaphore(0);
    }

    public boolean isEmpty() {
        return messages[head] == 0;
    }

    public boolean isFull() {
        if((tail + 1) % size == head) {
            return true;
        } else {
            return false;
        }
    }

    public char removeMessage() {
        try {
            dequeueSemaphore.acquire();
        } catch (InterruptedException e) {}

        // remove the element from the queue and update the head index
        char msg = messages[head];
        head = (head + 1) % size;
    
        // release a permit to the enqueue semaphore
        enqueueSemaphore.release();
    
        return msg;
    }


    @Override
    public boolean Send(char msg) {
        try {
            if(isFull()) {
                dequeueSemaphore.release();
                return false;
            } else {
                enqueueSemaphore.acquire();
                messages[tail] = msg;
                tail = (tail + 1) % size;
                dequeueSemaphore.release();
                return true;
            }

        } catch (InterruptedException e) {}
        return false;
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
