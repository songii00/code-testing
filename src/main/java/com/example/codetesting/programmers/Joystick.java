package com.example.codetesting.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * 프로그래머스 level 2. 조이스틱
 */
public class Joystick {

    /**
     * 한번 조이스틱을 움직일 때 발생하는 조작 수
     */
    private static int ONE_MOVEMENT_COUNT = 1;

    public static int solution(String name) {
        String[] array = name.split("");
        Map<String, Integer> map = getMap();
        return getMin(array,map);
    }

    /**
     * 조이스틱 움직임의 최소 수 Map 만들기 (세로)
     * @return
     */
    private static Map<String, Integer> getMap(){
        String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Map<String, Integer> map = new HashMap<>();

        int count =1;
        for(String t : a.split("")){
            map.put(t, Math.min(count, 28-count));
            count++;
        }
        return map;
    }

    /**
     * 최소 조작 수 구하기
     * @param array
     * @param map
     * @return
     */
    private static int getMin(String[] array, Map<String, Integer> map) {
        Target[] targets =  Arrays.stream(array).map(Target::new).toArray(Target[] :: new);

        int i = 0;
        int min = 0;
        while(true){
            Order order = getOrder(targets, i);
            targets[i].isRead = true;
            min += (ONE_MOVEMENT_COUNT + getIndividualCount(targets[i], map));
            i = order.change(i, targets.length);
            if(isEnd(targets)){
                min -=ONE_MOVEMENT_COUNT;
                break;
            }
        }
        return min;
    }

    /**
     * 타켓에 대한 조이스틱 움직임의 최소 수(세로)
     * @param target
     * @param map
     * @return
     */
    private static int getIndividualCount(Target target, Map<String, Integer> map) {
        return map.get(target.a) -1;
    }

    /**
     * 종료 확인
     * @param targets
     * @return
     */
    private static boolean isEnd(Target[] targets){
        return Arrays.stream(targets).allMatch(t -> t.isRead);
    }

    /**
     * 조작 방향 구하기
     * @param targets
     * @param targetIndex
     * @return
     */
    private static Order getOrder( Target[] targets, int targetIndex){
        int naturalOrder = getGreedyCountBy(Order.NATURAL, targets, targetIndex);
        int reverseOrder = getGreedyCountBy(Order.REVERSE, targets,targetIndex);
        if(naturalOrder <= reverseOrder){
            return Order.NATURAL;
        }
        return Order.REVERSE;
    }

    /**
     * A가 아닌 최소의 조작 수 구하기(가로)
     * @param order
     * @param targets
     * @param targetIndex
     * @return
     */
    private static int getGreedyCountBy(Order order, Target[] targets, int targetIndex){
        int i = targetIndex;
        int count = 0;
        while(true){
            i = order.change(i, targets.length);
            if(i==targetIndex){
                count--;
                break;
            }
            Target target = targets[i];
            count++;
            if(target.isRead){
                continue;
            }
            if(target.a.equals("A")){
                continue;
            }
            break;
        }
        return count;
    }

    /**
     * 조작 방향
     */
    enum Order {
        NATURAL((i, length) -> {
            i += ONE_MOVEMENT_COUNT;
            if(i >= length){
                i = 0;
            }
            return i;
        }), REVERSE((i, length) -> {
            i-=ONE_MOVEMENT_COUNT;
            if(i <= -1){
                i = length-1;
            }
            return i;
        });

        private BinaryOperator<Integer> changeIndex;

        int change(Integer i, Integer length){
            return this.changeIndex.apply(i,length);
        }

        Order( BinaryOperator<Integer> changeIndex){
            this.changeIndex =changeIndex;
        }
    }

    static class Target {
        String a;
        boolean isRead;
        Target(String a){
            this.a = a;
            this.isRead = a.equals("A");
        }
    }
}