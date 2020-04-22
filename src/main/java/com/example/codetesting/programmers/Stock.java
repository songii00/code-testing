package com.example.codetesting.programmers;

/**
 * 프로그래머스 level 2 주식가격
 */
public class Stock {
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i=0; i<prices.length; i++){
            int target = prices[i];
            int count = 0;
            if(i == prices.length-1){
                answer[i] = 0;
                break;
            }
            for(int j=i+1; j<prices.length; j++){
                int compare = prices[j];
                count++;
                if(target > compare){
                    break;
                }
            }
            answer[i] = count;
        }
        return answer;
    }
}
