package com.example.codetesting.Algorithm;

import java.util.Arrays;

public class Search {
    /**
     * 이진탐색
     * Binary Search
     * 시간복잡도 O(logN)
     */
    public static void binarySearch(){
        int[] array = new int[] {10,4,60,3,35, 15,23, 45, 56,57};
        int number =45;

        Arrays.sort(array);
        // 3 4 10 15 23 35 45 56 57

        int first = 0;
        int last =  array.length-1;
        int middle;

        while(first <= last){
            middle = (first + last) /2;
            if(array[middle] < number ){
                first = middle+1;
                continue;
            }
            if(array[middle] == number){
                System.out.println("answer : " + array[middle]);
                break;
            }
            last = middle -1;

        }

    }


}
