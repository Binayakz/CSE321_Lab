/*
 *   @author: Binayak Kumar dey
 *   Date: 02-04-2021
 */

import java.util.Random;

public class Task3 {

    public static void main(String[] args) {


        int [] array = new int[1000000];

        Random random = new Random();
//        Initializing the array with 100000 random integers
        for (int i = 0; i < array.length; i++) {
           array[i] = random.nextInt(100000000) ;
        }

        System.out.println("Unsorted Array:First 100 Elements of the Unsorted Array:");
        for (int i = 0; i < 100; i++) {

            System.out.print(array[i] + " ");
        }

//        Dividing the array into two sub array
        int [] subArray1 = new int[array.length / 2];
        int [] subArray2 = new int[array.length - (array.length / 2)];

        System.arraycopy(array, 0, subArray1, 0, array.length/2);
        System.arraycopy(array, array.length/2, subArray2, 0, array.length - array.length/2);

        /*
        * Initializing two threads
        * thread1 is for sorting the left part of the main array
        * thread2 is for sorting the right part
        * */

        long startTime = System.currentTimeMillis();

        ThreadCreation thread1 = new ThreadCreation(subArray1);
        ThreadCreation thread2 = new ThreadCreation(subArray2);


        thread1.start();
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Merging the two parts of the Array
        array = finalMerge(thread1.getArr(), thread2.getArr());

        long endTime = System.currentTimeMillis();

        System.out.println("\n\n========================\n\n");
        System.out.println("First 100 Elements of the Sorted Array:");
        for (int i = 0; i < 100; i++) {

            System.out.print(array[i] + " ");
        }

        System.out.println("\n\n========================\n\n");
        System.out.println("Total Execution Time to sort 1 million unsorted integers is: "+ (endTime-startTime) + " milli-seconds");

    }

    /*
    * finalMerge method to Merge the left and right part of the main array
    * */
    private static int[] finalMerge(int[] arr, int[] arr1) {
        int [] finalArray = new int[arr.length + arr1.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < arr.length && j < arr1.length) {

            if (arr[i] <= arr1[j]) {
                finalArray[k] = arr[i];
                i++;
                k++;
            }

            else {
                finalArray[k] = arr1[j];
                j++;
                k++;
            }

            if (i == arr.length) {
                while (j < arr1.length) {
                    finalArray[k] = arr1[j];
                    j++;
                    k++;
                }
            }

            if (j == arr1.length) {
                while (i < arr.length) {
                    finalArray[k] = arr[i];
                    i++;
                    k++;
                }
            }
        }
        return finalArray;
    }
}

class ThreadCreation extends Thread{

    private final int [] arr;

    public ThreadCreation(int[] arr) {
        this.arr = arr;
    }

    public int [] getArr() {
        return arr;
    }

    @Override
    public void run() {
        mergeSort(arr, 0, arr.length);
    }

//    Dividing
    public static void mergeSort(int[] a, int start, int end)
    {
        if (end - start >= 2) {

            int mid = (start + end) / 2;

            mergeSort(a, start, mid);
            mergeSort(a, mid, end);
            merge(a, start, mid, end);
        }
    }

    //    Combining -> merging
    public static void merge(int[] input, int start, int mid, int end) {

        if (input[mid - 1] < input[mid]) {
            return;
        }

        int i = start;
        int j = mid;
        int tempIndex = 0;
        int[] temp = new int[end - start];

        while (i < mid && j < end) {
            temp[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++];
        }

        System.arraycopy(input, i, input, (start + tempIndex), mid - i);
        System.arraycopy(temp, 0, input, start, tempIndex);
    }
}