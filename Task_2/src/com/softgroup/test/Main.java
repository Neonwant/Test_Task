package com.softgroup.test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] arr = new int [10];

        randomFill(arr);
        printArray(arr);

        Arrays.sort(arr);    // time complexity is O(n * log n)

        printArray(arr);

        isComplementary(arr, 18); // time complexity is O(n)
    }

    public static boolean isComplementary (int[] source, int K) {
        int left = 0, right = source.length - 1;
        while(true) {
            if (left == right) {
                System.out.println("There is no K-complementary pair");
                return false;
            }
            if ((source[left] + source[right]) < K) left++;
            else if ((source[left] + source[right])> K) right--;
            else break;
        }
        System.out.println(K + " = arr[" + left + "] + arr[" + right + "]");
        return true;
    }

    public static void randomFill (int[] source) {
        for (int i = 0; i < source.length; i++) {
            source[i] = (int)(Math.random() * 20);
        }
    }
    public static void printArray(int[] source) {
        for (int i = 0; i < source.length - 1; i++) {
            System.out.print(source[i] + ", ");
        }
        System.out.println(source[source.length - 1]);
    }


}
