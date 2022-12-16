package Multithreading.Semaphore;

public class Task3 {

    public void run() {

        boolean run = true;
        CircularQueue queue = new CircularQueue(5);
 
        Thread sA = new Thread(() -> {
            try {
                while(run == true) {
                    queue.enqueueSemaphore.acquire();
                    queue.Send('A');
                } 
            } catch (InterruptedException e) {}
            
        });

        Thread sB = new Thread(() -> {
            try {
                while(run == true) {
                    queue.enqueueSemaphore.acquire();
                    queue.Send('B');
                }
            } catch (InterruptedException e) {}
            
        });

        Thread sC = new Thread(() -> {
            try {
                while(run == true) {
                    queue.enqueueSemaphore.acquire();
                    queue.Send('C');
                }
            } catch (InterruptedException e) {}
            
        });

        Thread reciever = new Thread(() -> {
            try {
                while(run == true) {
                    queue.dequeueSemaphore.acquire();
                    System.out.print(queue.Recv());
                }
            } catch (InterruptedException e) {}
        });

        sA.start();
        sB.start();
        sC.start();

        reciever.start();

    }

}
