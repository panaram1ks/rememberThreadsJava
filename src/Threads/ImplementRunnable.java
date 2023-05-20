package Threads;

/**
 * @author E.Parominsky 18/05/2023 08:58
 */
public class ImplementRunnable implements Runnable {

    private int threadIndex;

    public ImplementRunnable(int index) {
        this.threadIndex = index;
    }

    @Override
    public void run() {
        int clientNumber = 1;
        while (clientNumber != 100) {
            System.out.println(threadIndex + " sent data to client: " + clientNumber);
            clientNumber++;
        }
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }


}
