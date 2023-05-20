package Threads;

/**
 * @author E.Parominsky 18/05/2023 08:31
 */
public class ServerThread extends Thread {

    public ServerThread(String threadName) {
        this.setName(threadName);
    }

    @Override
    public void run() {
        int clientNumber = 1;
        while (clientNumber != 100) {
            System.out.println(this.getName() + " send date to client: " + clientNumber + " priority: " + this.getPriority());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clientNumber++;
        }
    }

}
