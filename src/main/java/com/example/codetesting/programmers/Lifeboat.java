package com.example.codetesting.programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * 프로그래머스 level 2. 구명보트 
 */
public class Lifeboat {
    public static int solution(int[] people, int limit) {
        int count = 0;
        Arrays.sort(people);
        Deque<Integer> deque = Arrays.stream(people).boxed().collect(Collectors.toCollection(ArrayDeque::new));

        while(!deque.isEmpty()){
            Integer weight = deque.pop();

            while(!deque.isEmpty()){
                Integer maxWeight = deque.removeLast();
                if(weight + maxWeight > limit){
                    count++;
                    continue;
                }
                break;
            }
            count++;
        }

        return count;
    }
}
