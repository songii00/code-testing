package com.example.codetesting.programmers;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 level 2 프린터
 */
public class Printer {
    class Priority {
        int number;
        boolean isSelected;

        Priority(int number,boolean isSelected ){
            this.number = number;
            this.isSelected = isSelected;
        }
    }

    public int solution(int[] priorities, int location) {
        Queue<Priority> queue = getQueue(priorities, location);
        return getPrintNumber(queue);
    }

    private Queue<Priority> getQueue(int[] priorities, int location){
        Queue<Priority> queue = new LinkedList<>();
        for(int p = 0; p<priorities.length; p++){
            queue.offer(new Priority(priorities[p], location == p));
        }
        return queue;
    }

    private int getPrintNumber(Queue<Priority> queue){
        int printNumber = 0;

        while(true){
            Priority p = queue.poll();
            if(queue.stream().anyMatch(q -> q.number >p.number)){
                queue.offer(p);
                continue;
            }
            printNumber++;
            if(p.isSelected){
                return printNumber;
            }
        }
    }
}
