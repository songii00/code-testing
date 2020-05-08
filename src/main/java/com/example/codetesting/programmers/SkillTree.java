package com.example.codetesting.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 프로그래머스 level 2 스킬트리 
 */
public class SkillTree {

    public int solution(String skill, String[] skill_trees) {
        String[] originArray = skill.split("");
        // 원본 리스트
        List<String> originList = new ArrayList<>(Arrays.asList(originArray));

        // 사용 가능한 스킬 트리 숫자
        int count = 0;

        // 스킬 트리들에 들어있는 스킬 하나씩 검사
        for(String s : skill_trees){
            // 사용가능여부 체크
            if(isAvail(originList, s.split(""))){
                // 사용 가능하다면 전체 카운트 숫자 늘리기
                count++;
            }
        }
        //전체 카운트 수 리턴
        return count;
    }

    private boolean isAvail(List<String> origins, String[] skill_trees){
        List<String> originList  = new ArrayList<>();
        originList.addAll(origins); // 원본 리스트

        Iterator<String> iterator = originList.iterator();

        while(iterator.hasNext()){
            String origin =  iterator.next();
            for(String s : skill_trees){
                if(!origin.equals(s) && originList.contains(s)){
                    return false;
                }
                if(origin.equals(s)){
                    iterator.remove();
                    break;
                }
            }
        }
        return true;
    }
}
