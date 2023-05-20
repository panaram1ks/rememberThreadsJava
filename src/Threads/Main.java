package Threads;

/**
 * @author E.Parominsky ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) {

//        int second = 1;
//        try {
//            while (second <= 100) {
//                Thread.sleep(200);
//                System.out.println("Seconds: " + second);
//                second++;
//            }
//        } catch (Exception e) {
//            System.out.println(e.getStackTrace());
//        }

//        Threads.ServerThread serverThread = new Threads.ServerThread("Server1");
//        Threads.ServerThread serverThread2 = new Threads.ServerThread("Server2");
//        serverThread2.setPriority(Thread.MAX_PRIORITY);
//        serverThread2.start();
//        serverThread.start();
        //        serverThread.start(); //IllegalThreadStateException

        ImplementRunnable implementRunnable1 = new ImplementRunnable(1);
        Thread thread1 = new Thread(implementRunnable1);
        thread1.start();

        ImplementRunnable implementRunnable2 = new ImplementRunnable(2);
        Thread thread2 = new Thread(implementRunnable2, "Server2");
        System.out.println(thread2.getName());
        thread2.start();

        ImplementRunnable implementRunnable3 = new ImplementRunnable(3);
        implementRunnable3.start();

        System.out.println("Program finished!");

    }
}