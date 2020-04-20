package com.example.codetesting.programmers;

import com.example.codetesting.ProgrammersApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Truck {

    public static int solution(int bridge_length, int weight, int[] truck_weights) {

        List<TruckTTL> ing = new ArrayList();
        List<TruckTTL> wait = new ArrayList();
        int timer = 0;

        // 1단계 : 기초 ttl 설정
        for(int truckWeight : truck_weights){
            wait.add(new TruckTTL(truckWeight, bridge_length));
        }

        while(true){

            // 첫 인덱스일때
            if(ing.size() == 0){
                ing.add(wait.get(0));
                decreaseTTL(ing);
                wait.remove(0);
                timer++;
                continue;
            }

            if(wait.size() > 0 && getSumWeight(ing) + wait.get(0).truckWeight <= weight){
                ing.add(wait.get(0));
                wait.remove(0);
            }
            decreaseTTL(ing);
            timer++;
            removeExpireTTL(ing);


            if(wait.size() ==0 && ing.size() == 0){
                return timer;
            }
        }
    }

    private static void removeExpireTTL(List<TruckTTL> ing) {
        Iterator iterator = ing.iterator();
        while(iterator.hasNext()){
            TruckTTL truckTTL = (TruckTTL) iterator.next();
            if(truckTTL.ttl<0){
                iterator.remove();
            }
        }

        for(TruckTTL truckTTL : ing){
            if(truckTTL.ttl <0){
                ing.remove(truckTTL);
            }
        }
    }

    private static int getSumWeight(List<TruckTTL> ing){
        int sum = 0;
        for(TruckTTL i : ing){
            sum += i.ttl > 0 ? i.truckWeight : 0;
        }
        return sum;
    }

    public static class TruckTTL {
        int truckWeight;
        int ttl;

        TruckTTL(int truckWeight, int ttl){
            this.truckWeight = truckWeight;
            this.ttl = ttl;
        }
        void decreaseTTL(){
            this.ttl -=1;
        }
    }

    private static void decreaseTTL(List<TruckTTL> truckTTLS){
        for(TruckTTL truckTTL : truckTTLS){
            truckTTL.decreaseTTL();
        }
    }

}
