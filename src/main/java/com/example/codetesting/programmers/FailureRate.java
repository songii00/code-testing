package com.example.codetesting.programmers;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 프로그래머스 level 1. 실패율
 */
public class FailureRate {
    public static int[] solution(int N, int[] stages) {
        // n for
        // 1 >> 1인거 -> 1 / 1-1 보다 크고 -> 8
        // 1  의 실패율은 ? 1/8 실패율 저장 배열에 저장
        // 2 >> 2인거 -> 3 / 2-1 보다 크고 -> 7
        // 2 의 실패율은? 3/7
        // 3 >> 3인거 -> 2 / 3-1보다 크고 -> 4
        // 3의 실패율은? 2/4
        // 만약에 각각 스테이지 번호가 없다면? 실패율은 0

        List<Double> failRates = new ArrayList<>();
        Map<Integer,Double> map = new HashMap<>();

        // stage number
        for(int i=1;i<=N;i++){
            int successMan = 0;
            int totalMan = 0;
            // man
            for (int stage : stages) {
                if (stage == i) {
                    successMan++;
                }
                if (i - 1 < stage) {
                    totalMan++;
                }
            }
            double failRate = successMan == 0 ? 0 : (double)successMan/totalMan;
            failRates.add(failRate);
            map.put(i, failRate);
        }
        // Map<int, double> map ;
        // double failRates list > 내림차순으로 정렬
        // 맵에서 찾아서 배열에 넣기

        List<Integer> results = new ArrayList<>();
        failRates = failRates.stream()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .collect(Collectors.toList());

        for(Double failRate : failRates){
            for(Integer stateNumberKey : map.keySet()){
                if(failRate.equals(map.get(stateNumberKey))){
                    results.add(stateNumberKey);
                }
            }
        }
        return results.stream().mapToInt(i-> i).toArray();
    }
}
