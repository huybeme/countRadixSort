package com.company;

/*
        Huy Le
        Comp545 Homework 4 - implement Radix Sort

        Notes:
            This program was designed to simply run and will not take in any inputs from user.
            Simply run the code and the code will sort the array 'int[] data' using radix sort.
            Feel free to change the values in data to sort different values within the array.
 */

public class Main {

    // method to print out an array of integers
    public static void printIntArr(int[] array){
        for (int i = 0; i < array.length; i++){
            if (i < array.length -1)
            System.out.print(array[i] + " ");
            else
                System.out.print(array[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // data used for demonstration
	    int[] data = {8233, 1434, 325, 2457, 7567, 85, 862, 1543, 3845, 2340, 126, 7, 6482, 2348, 9836, 7635, 4021};
	    int[] data2 = {15,7,4,3,1,7,5,2,1,8,0,9,4,7,2,4,7,9,8,5,3};

	    System.out.println("\n|***** Testing counting sort *****|");      // done for practice - definitive way to sort integers
        System.out.print("unsorted array: ");
        printIntArr(data2);

        System.out.print("sorted array:   ");
        countingSort(data2);
        printIntArr(data2);

        System.out.println("\n|***** Radix Sort *****|");
        System.out.print("unsorted array: \t\t\t  ");
        printIntArr(data);

        System.out.print("sorted using counting sort:   ");             // done to confirm radix sort function - radix sort must match this
        countingSort(data);
        printIntArr(data);

        System.out.print("sorted using radix sort: \t  ");
        radixSort(data);
        printIntArr(data);
    }

    // max value aids the determination of the maximum number of digits
    public static int getMaxValue(int[] data){
        int max = data[0];                          // assign max as the first element array
        for (int i = 0; i < data.length; i++){      // iterate through array and replace max value when an element is greater
            if (max < data[i])
                max = data[i];
        }
        return max;
    }

    public static void radixSort(int[] array){
        int max = getMaxValue(array);
        for (int place = 1; max / place > 0; place = place * 10){       // iterate until place value becomes greater than max
            countRadixSort(array, place);
        }
    }

    // counting sort needed for radix sort
    public static void countRadixSort(int[] array, int place){
        int[] countArray = new int[getMaxValue(array) + 1];     // get running count for sorting; need +1 to correct for index 0
        int[] sortedArray = new int[array.length + 1];          // sorted array will be assigned here

        // tally counts of elements from provided unsorted array based on place
        for (int i = 0; i < array.length; i++)
            countArray[(array[i] / place) % 10]++;      // increase count of the value at current place

        // get a running count to each element for index determination for sorting
        for (int i = 1; i < 10; i++)
            countArray[i] = countArray[i] + countArray[i - 1];

        // fill in the sorted array using index of count array and values of array
        for (int i = array.length - 1; i >= 0; i--) {
            countArray[(array[i] / place) % 10]--;          // remove a tally as values are added to the sorted array
            sortedArray[countArray[(array[i] / place) % 10]] = array[i];
        }

        // re-assign passed array
        for (int i = 0; i < array.length; i++)
            array[i] = sortedArray[i];
    }

    // counting sort not to be used with radix sort
    public static void countingSort(int[] array){
        int[] countArray = new int[getMaxValue(array) +1];   // get running count for sorting; need +1 to correct for index 0
        int[] sortedArray = new int[array.length];           // sorted array

        // tally counts of elements from provided unsorted array
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]]++;
        }

        // get a running count to each element for index determination for sorting
        for (int i = 1; i < countArray.length; i++){
            countArray[i] = countArray[i] + countArray[i-1];
        }

        // sort array by counting sort
        for (int i = sortedArray.length -1; i >= 0; i--){
            countArray[array[i]]--;
            sortedArray[countArray[array[i]]] = array[i];
        }

        // re-assign passed array
        for (int i = 0; i < array.length; i++)
            array[i] = sortedArray[i];
    }
}
