/*
 *   @author: Binayak Kumar dey
 *   Date: 02-04-2021
 */

public class Task2_Bonus {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        MaxDivFinder thread0 = new MaxDivFinder(1, 10000);
        MaxDivFinder thread1 = new MaxDivFinder(10001, 20000);
        MaxDivFinder thread2 = new MaxDivFinder(200001, 30000);
        MaxDivFinder thread3 = new MaxDivFinder(30001, 40000);
        MaxDivFinder thread4 = new MaxDivFinder(40001, 50000);
        MaxDivFinder thread5 = new MaxDivFinder(50001, 60000);
        MaxDivFinder thread6 = new MaxDivFinder(60001, 70000);
        MaxDivFinder thread7 = new MaxDivFinder(70001, 80000);
        MaxDivFinder thread8 = new MaxDivFinder(80001, 90000);
        MaxDivFinder thread9 = new MaxDivFinder(90001, 100000);

        thread0.run();
        thread1.run();
        thread2.run();
        thread3.run();
        thread4.run();
        thread5.run();
        thread6.run();
        thread7.run();
        thread8.run();
        thread9.run();

        try {
            thread0.join();
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
            thread9.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Integer with Maximum Divisors: "+ MaxDivisorStore.get_Max_Divisors_Numbers());
        System.out.println("Number of Divisors: " + MaxDivisorStore.get_Max_Divisor());

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Execution Time (using single threading) : " + executionTime + " milli-seconds");

    }
}
