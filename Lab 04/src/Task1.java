/*
*   @author: Binayak Kumar dey
*   Date: 02-04-2021
*/

public class Task1 {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }

                System.out.println();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 11; i <= 20; i++) {
                            System.out.println(Thread.currentThread().getName() + ": " + i);
                        }
                    }
                }, "Thread 02");

                thread2.start();

                try {
                    thread2.join();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println();

                for (int i = 21; i <= 30; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }

        }, "Thread 01").start();
    }
}
