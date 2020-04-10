package com.example.codetesting;

import com.example.codetesting.programmers.FailureRate;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgrammersApplication {

    public static void main(String[] args) {
        int[] stages = new int[]{30, 1, 21, 17, 28};
        int[] orderedStages= FailureRate.solution(3, stages);
        for(int stage : orderedStages){
            System.out.print(stage + " ");
        }
    }
}


