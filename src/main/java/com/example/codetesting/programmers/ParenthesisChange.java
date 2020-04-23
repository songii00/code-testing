package com.example.codetesting.programmers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 프로그래머스 level 2 괄호 변환
 */
public class ParenthesisChange {
    public String solution(String s) {

        if(s.equals("")){
            return s;
        }

        String[] splitArray = s.split("");

        // 균형잡힌 문자열 구하기
        StringBuilder u = new StringBuilder();
        String v = "";
        int left = 0;
        int right = 0;
        for(int i=0;i<splitArray.length;i++){
            if(splitArray[i].equals("(")){
                left++;
            }
            if(splitArray[i].equals(")")){
                right++;
            }
            u.append(splitArray[i]);

            if(left == right){
                // 나머지 다넣기
                for(int j=i+1; j<splitArray.length;j++){
                    v+=splitArray[j];
                }
                break;
            }
        }

        // u 올바른 문자열 확인
        if(isCorrectString(u.toString())){
            String returnString = solution(v);
            return u + returnString;
        }

        // 안올바름
        String returnString = solution(v); //v 에 대해 재귀 // 빈 문자열나올떄까지
        // 앞뒤 문자 제거 + 나머지 괄호 뒤집기
        u = new StringBuilder(deleteAndConverse(u.toString()));
        return "(" + returnString + ")" + u;
    }

    private String deleteAndConverse(String s){
        List<String> strings = Arrays.stream(s.split("")).collect(Collectors.toList());
        // 앞 뒤 문자 제거
        strings.remove(0);
        strings.remove(strings.size()-1);

        StringBuilder result = new StringBuilder();
        for(String t : strings){
            // 반대로
            if(t.equals("(")){
                result.append(")");
                continue;
            }
            result.append("(");
        }
        return result.toString();
    }

    /**
     * 올바른 괄호 문자열 여부 검사
     * @param s
     * @return
     */
    private boolean isCorrectString(String s){
        String[] stringArray = s.split("");

        int left = 0;
        int right = 0;
        for(String t : stringArray){
            if(t.equals("(")){
                left++;
            }
            if(t.equals(")")){
                right++;
            }
            if(left < right){
                return false;
            }
        }
        return true;
    }
}