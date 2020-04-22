package com.example.codetesting.programmers;

import java.util.Stack;

/**
 * 프로그래머스 level 2 쇠막대기
 */
public class IronBar {

    public int solution(String a) {
        String[] replacedArray = a.replace("()", "-")
                .split("");

        Stack<String> stack = new Stack<>();
        int count = 0;
        for(String r : replacedArray){
            if(r.equals(")")){
                int specialCharCount = 0;
                while(true){
                    String s = stack.pop();
                    if(s.equals("-")){
                        specialCharCount++;
                    }
                    if(s.equals("(")){
                        break;
                    }
                }
                count += (specialCharCount+1);
                for(int i=1;i<=specialCharCount; i++){
                    stack.push("-");
                }
                continue;
            }
            stack.push(r);
        }
        return count;
    }
}
