package com.example.codetesting.programmers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 프로그래머스 level 3 [1차] 추석 트래픽
 */
public class ThanksgivingTraffic {
    private static List<LocalDateTime> countingTimes = new ArrayList<>();
    private static List<ResponseTimes> responseTimes = new ArrayList<>();
    private final int SECOND_NANO = 1000000000;
    private final int MICRO_SECOND_NANO = 1000000;

    public int solution(String[] lines) {
        setListBy(lines);

        int responseMaxCount = 0;
        for(LocalDateTime countingStartTime : countingTimes){
            LocalDateTime countingEndTime = plusSecond(countingStartTime);

            int responseCount = 0;
            for (ResponseTimes responseTime : responseTimes) {
                if (isInside(countingStartTime, countingEndTime, responseTime)) {
                    responseCount++;
                }
            }
            responseMaxCount = Math.max(responseCount, responseMaxCount);
        }
        return responseMaxCount;
    }

    /**
     * 응답 시간
     */
    static class ResponseTimes implements Comparable<ResponseTimes>{
        /**
         * 응답 시작 시간
         */
        LocalDateTime startDatetime;
        /**
         * 응답 종료 시간
         */
        LocalDateTime endDatetime;

        ResponseTimes(LocalDateTime startDatetime, LocalDateTime endDatetime) {
            this.startDatetime = startDatetime;
            this.endDatetime = endDatetime;
        }

        /**
         * 응답 시간을 응답 시작 시간 기준으로 정렬
         * @param o
         * @return
         */
        @Override
        public int compareTo(ResponseTimes o) {
            if(this.startDatetime.compareTo(o.startDatetime) <=0){
                return -1;
            }
            return 1;
        }
    }

    /**
     * 1초 구간 안에 응답시간이 속하는지 체크
     * @param countingStartTime
     * @param countingEndTime
     * @param p
     * @return
     */
    private boolean isInside(LocalDateTime countingStartTime , LocalDateTime countingEndTime, ResponseTimes p){
        if(countingStartTime.isAfter(p.endDatetime)){
            return false;
        }
        return countingEndTime.compareTo(p.startDatetime) >= 0;
    }

    private void setListBy(String[] lines){
        for(String line : lines){
            String[] dates = line.split(" ");

            LocalDateTime endDatetime = LocalDateTime.of(LocalDate.parse(dates[0]),
                    LocalTime.parse(dates[1], DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));
            long processNonos = converTotNonos(dates[2].substring(0,dates[2].length()-1));
            LocalDateTime startDatetime = endDatetime.minusNanos(processNonos);

            responseTimes.add(new ResponseTimes(startDatetime, endDatetime));
            countingTimes.add(startDatetime);
            countingTimes.add(endDatetime);

        }
        countingTimes.sort(Comparator.naturalOrder());
        Collections.sort(responseTimes);
    }

    private LocalDateTime plusSecond(LocalDateTime dateTime){
        return dateTime.plusNanos(SECOND_NANO-MICRO_SECOND_NANO);
    }

    private long converTotNonos(String seconds){
        return (long) (Double.valueOf(seconds)*SECOND_NANO)-MICRO_SECOND_NANO;
    }
}
