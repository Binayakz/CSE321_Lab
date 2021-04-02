/*
 *   @author: Binayak Kumar dey
 *   Date: 02-04-2021
 */

public class Task2 {

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

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();

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

        System.out.println("Execution Time (using multithreading) : " + executionTime + " milli-seconds");

    }
}


class MaxDivFinder extends Thread{

    int startRange;
    int endRange;

    public MaxDivFinder(int startRange, int endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    @Override
    public void run() {
        max_div_find(startRange, endRange);
    }

    void max_div_find(int start, int end){
        for (int i = start; i <= end; i++) {
            int divCount = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0)
                    divCount++;
            }

            if (divCount > MaxDivisorStore.get_Max_Divisor()) {
                MaxDivisorStore.setMax_Divisors_Numbers(i);
                MaxDivisorStore.setMax_Divisors(divCount);
            }
        }
    }
}


class MaxDivisorStore {
    static int max_Divisors = 0;
    static int max_Divisors_Numbers = 1;

    static int get_Max_Divisor() {
        return max_Divisors;
    }

    static int get_Max_Divisors_Numbers() {
        return max_Divisors_Numbers;
    }

    static void setMax_Divisors(int max_Divisors) {
        MaxDivisorStore.max_Divisors = max_Divisors;
    }

    static void setMax_Divisors_Numbers(int max_Divisors_Numbers) {
        MaxDivisorStore.max_Divisors_Numbers = max_Divisors_Numbers;
    }
}
